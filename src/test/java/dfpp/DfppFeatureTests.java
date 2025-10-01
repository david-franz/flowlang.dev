package dfpp;

import dfpp.ast.gen.DfppLexer;
import dfpp.ast.gen.DfppParser;
import dfpp.front.Parser2Ast;
import dfpp.front.TypeChecker;
import dfpp.backend.CodeGen;
import dfpp.ast.Ast;
import dfpp.ast.Ast.Top;
import dfpp.ast.Ast.FnDecl;
import org.antlr.v4.runtime.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class DfppFeatureTests {

    @Test
    public void runAllDfppTests() throws Exception {
        Path testsDir = Path.of("tests");
        assertTrue(Files.isDirectory(testsDir), "tests directory not found");

        Set<String> skip = loadSkipList(testsDir.resolve("skiplist.txt"));
        if (!skip.isEmpty()) {
            // Start with all tests disabled; incrementally enable by removing from skiplist.txt
            return;
        }
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(testsDir, "*.dfpp")) {
            for (Path df : stream) {
                String base = df.getFileName().toString().replaceFirst("\\.dfpp$", "");
                Path expected = testsDir.resolve(base + ".expected");
                if (!Files.exists(expected)) {
                    fail("Missing expected file for test " + df);
                }
                String out = compileAndRun(df);
                String expectedStr = Files.readString(expected, StandardCharsets.UTF_8).replace("\r\n", "\n").trim();
                String actualStr = out.replace("\r\n", "\n").trim();
                assertEquals(expectedStr, actualStr, "Output mismatch for " + df.getFileName());
            }
        }
    }

    private static Set<String> loadSkipList(Path skipFile) throws IOException {
        Set<String> s = new HashSet<>();
        if (Files.exists(skipFile)) {
            for (String line : Files.readAllLines(skipFile, java.nio.charset.StandardCharsets.UTF_8)) {
                String t = line.trim();
                if (t.isEmpty() || t.startsWith("#")) continue;
                s.add(t);
            }
        }
        return s;
    }

    private static String compileAndRun(Path srcFile) throws Exception {
        // Parse
        CharStream input = CharStreams.fromPath(srcFile);
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
        // Merge imported modules
        for (var entry : astParser.getImports().entrySet()) {
            String moduleName = entry.getValue();
            Path filePath = findModuleFile(moduleName);
            CharStream impInput = CharStreams.fromPath(filePath);
            DfppLexer impLexer = new DfppLexer(impInput);
            CommonTokenStream impTokens = new CommonTokenStream(impLexer);
            DfppParser impParser = new DfppParser(impTokens);
            impParser.removeErrorListeners();
            impParser.addErrorListener(new BaseErrorListener() {
                @Override public void syntaxError(Recognizer<?, ?> r, Object off, int line, int col, String msg, RecognitionException e) {
                    throw new RuntimeException("parse error in imported module '"+moduleName+"' at "+line+":"+col+" "+msg);
                }
            });
            Ast.Program impAst = new Parser2Ast().build((DfppParser.ProgramContext) impParser.program());
            for (Top t : impAst.tops()) {
                if (t instanceof FnDecl f && f.name().equals("main")) continue;
                ast.tops().add(t);
            }
        }

        // Type check
        TypeChecker.check(ast);

        // Compile to a throwaway class under out/tests
        String classIntName = ("tests/" + srcFile.getFileName().toString().replaceAll("\\.dfpp$", "")).replace('-', '_');
        byte[] bytes = CodeGen.compile(classIntName, ast);
        Path outPath = Path.of("out", classIntName + ".class");
        CodeGen.writeToFile(bytes, outPath);

        // Load and run f$main
        var loader = new java.net.URLClassLoader(new java.net.URL[]{ Path.of("out").toUri().toURL() });
        Class<?> cls = Class.forName(classIntName.replace('/', '.'), true, loader);
        Method m = cls.getMethod("f$main", Object[].class);

        // Capture System.out
        PrintStream prevOut = System.out;
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buf, true, StandardCharsets.UTF_8));
        try {
            m.invoke(null, (Object) new Object[0]);
        } finally {
            System.setOut(prevOut);
        }
        return buf.toString(StandardCharsets.UTF_8);
    }

    private static Path findModuleFile(String moduleName) {
        List<Path> roots = List.of(Path.of("tests"), Path.of("examples"));
        for (Path root : roots) {
            if (!Files.exists(root)) continue;
            try {
                try (var files = Files.walk(root)) {
                    Optional<Path> hit = files
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
                }
            } catch (IOException e) {
                // ignore and continue
            }
        }
        throw new RuntimeException("Module not found: " + moduleName);
    }
}
