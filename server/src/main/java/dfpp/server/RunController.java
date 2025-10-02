package dfpp.server;

import dfpp.ast.Ast;
import dfpp.ast.gen.DfppLexer;
import dfpp.ast.gen.DfppParser;
import dfpp.backend.CodeGen;
import dfpp.front.Parser2Ast;
import dfpp.front.TypeChecker;
import org.antlr.v4.runtime.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;

@RestController
public class RunController {

    public static record RunRequest(String code) {}
    public static record RunResponse(String stdout, String stderr) {}

    @PostMapping(path = "/run", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RunResponse run(@RequestBody RunRequest req) {
        String code = req.code()==null? "" : req.code();
        String stdout = "";
        String stderr = "";
        try {
            // Parse from in-memory string
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
            // No import resolution in REPL for safety/simplicity (single unit only)

            TypeChecker.check(ast);

            String classIntName = "repl/Main";
            byte[] bytes = CodeGen.compile(classIntName, ast);

            // Define class in an isolated loader
            ClassLoader parent = RunController.class.getClassLoader();
            class InMemLoader extends ClassLoader {
                InMemLoader(ClassLoader p) { super(p); }
                public Class<?> define(String name, byte[] b) { return super.defineClass(name, b, 0, b.length); }
            }
            InMemLoader loader = new InMemLoader(parent);
            Class<?> cls = loader.define(classIntName.replace('/', '.'), bytes);
            Method main = cls.getMethod("f$main", Object[].class);

            // Capture stdout during invocation
            PrintStream prev = System.out;
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            System.setOut(new PrintStream(buf));
            try {
                main.invoke(null, (Object) new Object[0]);
            } finally {
                System.setOut(prev);
            }
            stdout = buf.toString();
        } catch (Exception ex) {
            stderr = ex.getClass().getSimpleName() + ": " + (ex.getMessage()==null? "" : ex.getMessage());
        }
        return new RunResponse(stdout, stderr.isEmpty()? null : stderr);
    }
}
