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

    // Inline allow-list of tests to run (basenames without extension). Default empty.
    private static final java.util.List<String> INCLUDE = java.util.List.of(
        // enable tests incrementally, e.g. "hello_test"
    );

    // Allow enabling entire categories (directories under tests/unit): Data, Expression, Types, Functions
    private static final java.util.List<String> INCLUDE_DIRS = java.util.List.of(
        // e.g. "Data", "Expression", "Functions"
            "Data",
            "Expression",
            "Functions",
            "Pattern",
            "Modules",
            "Types"
    );

    // Inline expected outputs keyed by test basename.
private static final java.util.Map<String, String> EXPECTED = java.util.Map.ofEntries(
        // Data
        java.util.Map.entry("list_idx", "20"),
        java.util.Map.entry("rec_get", "9"),
        java.util.Map.entry("list_idx0", "5"),
        java.util.Map.entry("rec_two", "1"),
        java.util.Map.entry("rec_nested", "3"),
        java.util.Map.entry("rec_btick", "7"),
        // Expression
        java.util.Map.entry("add", "5"),
        java.util.Map.entry("and", "false"),
        java.util.Map.entry("or", "true"),
        java.util.Map.entry("lt", "true"),
        java.util.Map.entry("ge", "true"),
        java.util.Map.entry("eq", "true"),
        java.util.Map.entry("tern", "t"),
        java.util.Map.entry("sub", "5"),
        java.util.Map.entry("mul", "42"),
        java.util.Map.entry("div", "4"),
        java.util.Map.entry("mod", "1"),
        java.util.Map.entry("le", "true"),
        java.util.Map.entry("gt", "true"),
        java.util.Map.entry("not_true", "false"),
        java.util.Map.entry("not_zero", "true"),
        java.util.Map.entry("paren", "20"),
        java.util.Map.entry("concat_str", "ab"),
        java.util.Map.entry("concat_mix", "x=7"),
        java.util.Map.entry("eq_str", "true"),
        java.util.Map.entry("ne", "true"),
        // Functions
        java.util.Map.entry("call", "7"),
        java.util.Map.entry("add2", "7"),
        // Pattern
        java.util.Map.entry("match_hit", "two"),
        java.util.Map.entry("match_miss", "?"),
        java.util.Map.entry("match_str", "ok"),
        java.util.Map.entry("match_bool", "T"),
        // Modules
        java.util.Map.entry("lib_const", "9"),
        java.util.Map.entry("use_const", "9"),
        // Types
        java.util.Map.entry("add_typed", "5")
    );

    @Test
    public void runIncludedDfppTests() throws Exception {
        if (INCLUDE.isEmpty() && INCLUDE_DIRS.isEmpty()) return; // nothing enabled
        Path testsRoot = Path.of("tests", "unit");
        assertTrue(Files.isDirectory(testsRoot), "tests/unit directory not found");

        // collect files from included dirs
        Set<Path> toRun = new java.util.LinkedHashSet<>();
        for (String dir : INCLUDE_DIRS) {
            Path cat = testsRoot.resolve(dir);
            if (!Files.isDirectory(cat)) continue;
            try (DirectoryStream<Path> ds = Files.newDirectoryStream(cat, "*.dfpp")) {
                for (Path p : ds) toRun.add(p);
            }
        }
        // collect files by explicit basenames
        if (!INCLUDE.isEmpty()) {
            try (var walk = Files.walk(testsRoot)) {
                Map<String, Path> byBase = new HashMap<>();
                walk.filter(p -> p.toString().endsWith(".dfpp")).forEach(p -> {
                    String base = p.getFileName().toString().replaceFirst("\\.dfpp$", "");
                    byBase.put(base, p);
                });
                for (String base : INCLUDE) {
                    Path p = byBase.get(base);
                    assertNotNull(p, "Test file not found for basename: " + base);
                    toRun.add(p);
                }
            }
        }
        if (toRun.isEmpty()) return; // nothing enabled

        java.util.List<Result> results = new java.util.ArrayList<>();
        java.util.List<Path> ordered = new java.util.ArrayList<>(toRun);
        ordered.sort(java.util.Comparator.comparing(Path::toString));
        for (Path df : ordered) {
            String base = df.getFileName().toString().replaceFirst("\\.dfpp$", "");
            Result r = runOne(df, base);
            results.add(r);
        }
        report(results);
    }

/* removed: skip list no longer used */

    private static record Result(String name, String expected, String actual, boolean ok, String note) {}

    private static Result runOne(java.nio.file.Path df, String base) {
        try {
            String out = compileAndRun(df);
            String actualStr = out.replace("\\r\\n", "\\n").trim();
            String expectedStr = EXPECTED.get(base);
            boolean hasExpected = expectedStr != null;
            boolean ok = hasExpected && expectedStr.equals(actualStr);
            System.out.println("[TEST] " + base);
            if (!actualStr.isEmpty()) System.out.println(actualStr);
            String note = hasExpected ? (ok ? "OK" : "mismatch") : "missing expected";
            return new Result(base, expectedStr, actualStr, ok, note);
        } catch (Exception ex) {
            System.out.println("[TEST] " + base + " ERROR: " + ex.getMessage());
            return new Result(base, EXPECTED.get(base), "<error>", false, ex.getClass().getSimpleName());
        }
    }

    private static void report(java.util.List<Result> results) {
        long passed = results.stream().filter(r -> r.ok).count();
        long total = results.size();
        long failed = total - passed;
        System.out.println(String.format("[SUMMARY] %d passed, %d failed, %d total", passed, failed, total));
        writeLog(results);
        if (failed > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failures (" + failed + "):\n");
            for (Result r : results) {
                if (r.ok) continue;
                sb.append(" - ").append(r.name).append(": ").append(r.note).append("\n");
                sb.append("   expected: ").append(String.valueOf(r.expected)).append("\n");
                sb.append("   actual  : ").append(String.valueOf(r.actual)).append("\n");
            }
            org.junit.jupiter.api.Assertions.fail(sb.toString());
        }
    }

    private static Set<String> loadSkipList(Path skipFile) throws IOException {
        throw new UnsupportedOperationException("Skip list removed; use INCLUDE list instead");
    }

    @Disabled("Run all tests present in tests/ (requires EXPECTED entries); opt-in only")
    @Test
    public void runAllDfppTests() throws Exception {
        Path testsRoot = Path.of("tests", "unit");
        assertTrue(Files.isDirectory(testsRoot), "tests/unit directory not found");
        java.util.List<Result> results = new java.util.ArrayList<>();
        try (var walk = Files.walk(testsRoot)) {
            walk.filter(p -> p.toString().endsWith(".dfpp"))
                .sorted()
                .forEach(df -> {
                    String base = df.getFileName().toString().replaceFirst("\\.dfpp$", "");
                    results.add(runOne(df, base));
                });
        }
        report(results);
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


    private static void writeLog(java.util.List<Result> results) {
        try {
            java.nio.file.Path outDir = java.nio.file.Path.of("build");
            java.nio.file.Files.createDirectories(outDir);
            java.nio.file.Path log = outDir.resolve("dfpp-tests.txt");
            StringBuilder sb = new StringBuilder();
            for (Result r : results) {
                sb.append("[TEST] ").append(r.name).append(" ").append(r.ok ? "OK" : r.note).append("\n");
                if (r.actual != null && !r.actual.isEmpty()) {
                    sb.append(r.actual).append("\n");
                }
                sb.append("\n");
            }
            long passed = results.stream().filter(x -> x.ok).count();
            sb.append(String.format("[SUMMARY] %d passed, %d failed, %d total\n",
                    passed, results.size()-passed, results.size()));
            java.nio.file.Files.writeString(log, sb.toString(), java.nio.charset.StandardCharsets.UTF_8);
            System.out.println("[LOG] Wrote test output to " + log.toString());
        } catch (Exception e) {
            System.out.println("[LOG] Failed to write test log: " + e.getMessage());
        }
    }
}