# df++ Run API (Spring Boot)

Local dev

- ./gradlew :server:bootRun
- POST http://localhost:8080/run with JSON { "code": "...df++ code..." }

Response

- { "stdout": "...", "stderr": "...optional..." }

Notes

- Imports are not resolved in REPL for safety. Single compilation unit only.
- Execution captures System.out and returns it as stdout.
- This service reuses the in-repo compiler pipeline (ANTLR → AST → TypeChecker → ASM) and runs f$main if present.
