package dfpp;

import dfpp.ast.gen.DfppLexer;
import dfpp.ast.gen.DfppParser;
import dfpp.front.Parser2Ast;
import dfpp.front.TypeChecker;
import dfpp.backend.CodeGen;
import dfpp.ast.Ast.Top;
import dfpp.ast.Ast.FnDecl;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTree;

import java.lang.reflect.Method;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Stream;

public final class Driver {

    public static void main(String[] args) throws Exception {
        try {
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
        parser.addErrorListener(new BaseErrorListener() {
            @Override public void syntaxError(Recognizer<?, ?> r, Object off, int line, int col, String msg, RecognitionException e) {
                throw new RuntimeException("parse error at "+line+":"+col+" "+msg);
            }
        });

        ParseTree tree = parser.program();
        var astParser = new Parser2Ast();
        var ast = astParser.build((DfppParser.ProgramContext) tree);
        // Merge and inline imported modules (so their functions/values are visible)
        for (var entry : astParser.getImports().entrySet()) {
            String moduleName = entry.getValue();
            Path filePath = findModuleFile(moduleName);
            var impInput = CharStreams.fromPath(filePath);
            var impLexer = new DfppLexer(impInput);
            var impTokens = new CommonTokenStream(impLexer);
            var impParser = new DfppParser(impTokens);
            impParser.removeErrorListeners();
            impParser.addErrorListener(new BaseErrorListener() {
                @Override public void syntaxError(Recognizer<?, ?> r, Object off,
                        int line, int col, String msg, RecognitionException e) {
                    throw new RuntimeException(
                        "parse error in imported module '"+moduleName+"' at "+line+":"+col+" "+msg);
                }
            });
            var impAst = new Parser2Ast()
                .build((DfppParser.ProgramContext) impParser.program());
            for (Top t : impAst.tops()) {
                if (t instanceof FnDecl f && f.name().equals("main")) continue;
                ast.tops().add(t);
            }
        }
        // Static type checking (primitives only)
        try {
            TypeChecker.check(ast);
        } catch (TypeChecker.TypeException ex) {
            System.err.println("Type error: " + ex.getMessage());
            return;
        }

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
            Object ret = m.invoke(null, (Object) new Object[0]); // varargs tramp
            if (ret != null) {
                System.out.println(ret);
            }
        } catch (NoSuchMethodException nsme) {
            System.out.println("No df++ fn main() found; nothing to run.");
        }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Driver error: " + ex.getMessage());
            System.exit(1);
        }
    }

    private static Path findModuleFile(String moduleName) {
        // Search both examples/ and tests/ for a file that declares the requested module
        List<Path> roots = List.of(Path.of("examples"), Path.of("tests"));
        for (Path root : roots) {
            if (!Files.exists(root)) continue;
            try (java.util.stream.Stream<Path> files = Files.walk(root)) {
                java.util.Optional<Path> hit = files
                        .filter(p -> p.toString().endsWith(".dfpp"))
                        .filter(p -> {
                            try {
                                return Files.lines(p)
                                        .map(String::trim)
                                        .anyMatch(l -> l.equals("module " + moduleName));
                            } catch (IOException e) {
                                return false;
                            }
                        })
                        .findFirst();
                if (hit.isPresent()) return hit.get();
            } catch (IOException e) {
                // keep searching other roots
            }
        }
        throw new RuntimeException("Failed to find module '" + moduleName + "' in examples/ or tests/");
    }
}
