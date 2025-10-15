# df++ (dfpp)

A small, strongly-typed language for the JVM that blends functional collections, lightweight records, and declarative task orchestration. This repo contains the v1 compiler, bytecode backend, examples, and a test harness.

Status: working v1 proof-of-concept

- Frontend: ANTLR4 grammar → typed AST; local type inference; structural types for records and first-order collections.
- Backend: JVM bytecode via ASM (JDK 21), one class per compilation unit; verifier-safe codegen.
- Runtime: tiny helper library (dfpp.rt.Rt) for arithmetic, comparisons, truthiness, list indexing/slicing.
- Tests: JUnit harness executes df++ files under tests/unit and checks expected stdout or expected errors.

Quick start

```bash
./gradlew clean build
./gradlew run --args="examples/hello_world.dfpp demo.hello"
```

Expected output

```
wrote out/demo/hello.class

== running df++ main ==

Hello, df++!
```

Highlights (v1)

- Static typing with local inference
  - Primitives: Int, String, Bool, Unit
  - Structural records: { field: Type, ... } with field access x.f
  - First-order polymorphic collections in annotations: List<T>, Set<T>, Map<K,V>
  - Inference for list literals and record literals; element/field types propagate through expressions
  - Basic tuple types in annotations: (T1, T2, ...)
- Expressions you expect
  - Arithmetic, comparisons, boolean ops with short-circuit, ternary, parentheses
  - Lists: literals [..], Python-style negative indices xs[-1], and slicing xs[a:b:c]
  - List comprehensions: [ f(x) for x in xs if p(x) ]
  - Records: { a = 1, b = "x" } and r.b
  - Pattern matching on literals and wildcard: match (e) { 1 -> "one", _ -> "?" }
- Declarative task orchestration
  - Define tasks with pre/act/pos: task 'Name' { pre P; act { ... }; pos Q }
  - Run tasks as statements: run('A')
  - Chain with then and group with parallel { 'A', 'B' } — sequential in v1 (sugared into multiple runs)
- Modules
  - import pkg.name as Alias; alias-qualified uses resolve at compile time and are merged into a single generated class

Why this implementation is solid

- Grammar-driven: ANTLR4 grammar with a small, explicit AST (Java records) keeps the surface area clear and testable.
- Static checks: a focused type checker validates parameter/return types, collection arity (List/Set/Map), list/slice indices, and catches obvious mismatches early.
- Verifier-safe bytecode: codegen uses COMPUTE_FRAMES, casts before interface calls, and emits Java collections in a way the verifier accepts (e.g., nested list indexing).
- Tight runtime surface: a minimal Rt class implements all dynamic ops; behavior is easy to audit and extend.
- Tests first: hundreds of small, categorized tests cover expressions, data structures, types, modules, pattern matching, and tasks. Error paths are tested as well.

Two-minute tour

- Hello

```text
module demo.hello

fn main() { print("Hello, df++!") }
```

- Comprehensions and slicing

```text
fn demo() {
  let xs = [1,2,3,4,5]
  print([x * x for x in xs if x % 2 == 1])  // [1, 9, 25]
  print(xs[-3:])                            // [3, 4, 5]
}
```

- Records and match

```text
fn pick(b: Bool): String =
  match (b) { true -> "T", _ -> "F" }

fn demo() {
  let u = { id = 7, name = "ada" }
  print(u.name) // ada
}
```

- Tasks and orchestration

```text
task 'A' { act { print("A") } }
task 'B' { act { print("B") } }

fn main() {
  run('A').then('B')
  parallel { 'A', 'B' }  // sequential in v1
}
```

Build and run your own file

```bash
./gradlew run --args="<path/to/file.dfpp> <pkg.or.name>"

# example
./gradlew run --args="examples/comp.dfpp demo.comp"
```

Running tests

- Tests live under tests/unit/<Category> (Collections, Data, Expression, Functions, Modules, Pattern, Tasks, Types).
- The JUnit harness enumerates enabled categories in src/test/java/dfpp/DfppFeatureTests.java and compares stdout to expected baselines.

```bash
./gradlew test
```

What’s implemented vs. not (v1)

Implemented

- Parser and AST for the subset described above
- Type checker with local inference; structural records; List/Set/Map recognition and arity checks; tuple types in annotations
- Code generation for functions, blocks, calls, lists, comprehensions, indexing/slicing, records/fields, literals, boolean short-circuit, ternary, and minimal match
- Tasks with pre/act/pos; run('name'); then/parallel sugar lowered to sequential runs

Intentional gaps for v1

- Pattern matching is limited to literals and wildcard (no record/variant destructuring yet)
- Member method calls and user ADTs are parsed in the grammar but not lowered/typed yet
- No separate module classes; imports are merged into one generated class per compile
- The long-term sandboxed runtime (dfVM), richer stdlib, and packaging are part of the design; see the paper for direction

Design paper

- df++ design doc (PDF): df++_design_doc.pdf
  The README stays focused on getting started; the paper covers the broader model (capabilities, replay, richer types/orchestration) and roadmap.

Repository layout (current)

- src/main/antlr: grammar
- src/main/java/dfpp: driver, frontend (parser→AST, type checker), backend (ASM), runtime helpers
- examples: small, runnable snippets
- tests: unit test corpus and JUnit harness

## Deployment (Azure)

GitHub Actions handle CI/CD for both the React frontend and the Spring Boot backend.

- **Frontend** – `.github/workflows/deploy-frontend.yml`
  - Builds the Vite app from `web/` with Node 20 and deploys the compiled assets in `web/dist/` to an Azure Static Web App.
  - Requires repository secrets:
    - `AZURE_STATIC_WEB_APPS_API_TOKEN` – deployment token from the Static Web App resource.
  - Optional variables:
    - `API_BASE` – overrides the backend base URL that is injected into the build (defaults to `https://api.dfpp.run`).

- **Backend** – `.github/workflows/deploy-backend.yml`
  - Builds `:server:bootJar` and publishes the resulting `server/build/libs/server-*.jar` to an Azure App Service.
  - Requires repository secrets:
    - `AZURE_CREDENTIALS` – JSON service-principal credentials accepted by `azure/login` (keys: `clientId`, `clientSecret`, `tenantId`, `subscriptionId`).
    - `AZURE_WEBAPP_NAME` – target App Service name.
  - Optional configuration via environment variables:
    - `ALLOWED_ORIGINS` – comma-separated list of origins allowed to call the API (defaults to `http://localhost:5173,https://flowlang.dev,https://www.flowlang.dev,https://*.azurestaticapps.net`).
  - The workflow uploads an `app.jar`; set the App Service startup command to `java -jar /home/site/wwwroot/app.jar`.

Both workflows trigger on pushes to `main` that touch their respective source trees and can also be run manually via the **Run workflow** button.

License

- This is a reference implementation under active development; a formal license will be added alongside the website/docs.
