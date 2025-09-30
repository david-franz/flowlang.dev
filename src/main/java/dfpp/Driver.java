package dfpp;

import dfpp.ast.gen.DfppLexer;
import dfpp.ast.gen.DfppParser;
import dfpp.front.Parser2Ast;
import dfpp.backend.CodeGen;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.*;

public final class Driver {

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("usage: dfpp <input.dfpp> <outClassName>");
            System.out.println("example: dfpp examples/hello.dfpp demo/hello/Main");
            return;
        }
        var srcFile = Path.of(args[0]);
        var classIntName = args[1].replace('.', '/');

        var input = CharStreams.fromPath(srcFile);
        var lexer = new DfppLexer(input);
        var tokens = new CommonTokenStream(lexer);
        var parser = new DfppParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new DiagnosticErrorListener());
        parser.addErrorListener(new BaseErrorListener() {
            @Override public void syntaxError(Recognizer<?, ?> r, Object off, int line, int col, String msg, RecognitionException e) {
                throw new RuntimeException("parse error at "+line+":"+col+" "+msg);
            }
        });

        ParseTree tree = parser.program();
        var ast = new Parser2Ast().build((DfppParser.ProgramContext) tree);

        byte[] bytes = CodeGen.compile(classIntName, ast);
        var outPath = Path.of("out", classIntName + ".class");
        CodeGen.writeToFile(bytes, outPath);
        System.out.println("wrote " + outPath);

        // Load and run main if present
        var loader = new java.net.URLClassLoader(new java.net.URL[]{ Path.of("out").toUri().toURL() });
        var cls = Class.forName(classIntName.replace('/', '.'), true, loader);

        try {
            Method m = cls.getMethod("f$main", Object[].class);
            System.out.println("== running df++ main ==");
            m.invoke(null, (Object) new Object[0]); // varargs tramp
        } catch (NoSuchMethodException nsme) {
            System.out.println("No df++ fn main() found; nothing to run.");
        }
    }
}
