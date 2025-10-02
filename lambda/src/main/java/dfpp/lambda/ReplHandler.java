package dfpp.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import dfpp.ast.Ast;
import dfpp.ast.gen.DfppLexer;
import dfpp.ast.gen.DfppParser;
import dfpp.backend.CodeGen;
import dfpp.front.Parser2Ast;
import dfpp.front.TypeChecker;
import org.antlr.v4.runtime.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ReplHandler implements RequestHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static class InReq { public String code; }
    public static class OutRes { public String stdout; public String stderr; }

    @Override
    public APIGatewayV2HTTPResponse handleRequest(APIGatewayV2HTTPEvent event, Context context) {
        String body = event.getBody();
        OutRes out = new OutRes();
        try {
            InReq req = body != null && !body.isEmpty() ? MAPPER.readValue(body, InReq.class) : new InReq();
            String code = req.code == null ? "" : req.code;

            CharStream input = CharStreams.fromString(code);
            DfppLexer lexer = new DfppLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            DfppParser parser = new DfppParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(new BaseErrorListener() {
                @Override public void syntaxError(Recognizer<?, ?> r, Object off, int line, int col, String msg, RecognitionException e) {
                    throw new RuntimeException("parse error at "+line+":"+col+" "+msg);
                }
            });
            DfppParser.ProgramContext tree = parser.program();
            Parser2Ast astParser = new Parser2Ast();
            Ast.Program ast = astParser.build(tree);
            // Imports are not resolved in Lambda REPL for safety.
            TypeChecker.check(ast);

            String classIntName = "repl/Main";
            byte[] bytes = CodeGen.compile(classIntName, ast);

            // Define and run
            ClassLoader parent = getClass().getClassLoader();
            class InMemLoader extends ClassLoader { InMemLoader(ClassLoader p){ super(p);} Class<?> define(String n, byte[] b){ return defineClass(n, b, 0, b.length);} }
            InMemLoader loader = new InMemLoader(parent);
            Class<?> cls = loader.define(classIntName.replace('/', '.'), bytes);
            Method main = cls.getMethod("f$main", Object[].class);

            PrintStream prev = System.out;
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            System.setOut(new PrintStream(buf, true, StandardCharsets.UTF_8));
            try {
                main.invoke(null, (Object) new Object[0]);
            } finally {
                System.setOut(prev);
            }
            out.stdout = buf.toString(StandardCharsets.UTF_8);
            out.stderr = null;
        } catch (Exception ex) {
            out.stdout = "";
            out.stderr = ex.getClass().getSimpleName() + ": " + (ex.getMessage()==null? "" : ex.getMessage());
        }

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        // CORS: adjust origins as needed
        headers.put("Access-Control-Allow-Origin", "https://dfpp.dev");
        headers.put("Access-Control-Allow-Headers", "Content-Type");
        headers.put("Access-Control-Allow-Methods", "POST, OPTIONS");

        try {
            String outJson = MAPPER.writeValueAsString(out);
            return APIGatewayV2HTTPResponse.builder()
                    .withStatusCode(200)
                    .withHeaders(headers)
                    .withBody(outJson)
                    .build();
        } catch (Exception e) {
            return APIGatewayV2HTTPResponse.builder()
                    .withStatusCode(500)
                    .withHeaders(headers)
                    .withBody("{\"stdout\":\"\",\"stderr\":\"serialization error\"}")
                    .build();
        }
    }
}
