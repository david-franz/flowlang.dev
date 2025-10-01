# df++ (dfpp + dfVM)

*A small, secure JVM language that blends OOP types, functional collections, and declarative tasks — with a mandatory sandbox runtime.*

> **Status:** This repository is the reference implementation of df++ (compiler, dfVM, standard library, and tooling).

> **Build & Run the example**:

```bash
./gradlew clean build
./gradlew run --args="examples/hello_world.dfpp demo.hello"

Expected output:

```
wrote out/demo/hello.class

== running df++ main ==

Hello, df++!
```

## Current status (v1 POC)

This repo contains a working proof-of-concept compiler and runtime for a minimal v1 subset. Implemented today:

- Frontend
  - ANTLR4 grammar and parser → AST (Java records)
  - Type checker: primitives (Int, String, Bool, Unit) plus basic first-order generics and structural records.
    - Recognizes List<T>/Set<T>/Map<K,V> in annotations and validates type arity (e.g., Set requires 1, Map requires 2 args)
    - Infers element types for list literals; flags element type mismatches
    - Infers record field types for `{ ... }` and propagates through `.` access
    - Supports nested collections in annotations and expression typing (e.g., List<List<Int>>; List<Set<Int>>; Map<String, List<Int>>)
    - Checks const/let, function parameter types (when declared), and basic return type consistency; best-effort return inference for nominal shapes
- Codegen/runtime
  - ASM bytecode emitter, single class per compiled unit, static fields for top-level values
  - Expressions: arithmetic (+ - * / %), comparisons (== != < <= > >=), boolean (! && || with short-circuit), ternary (cond ? a : b), parentheses
  - Data: list literals + indexing (with verifier-safe nested indexing); record literals + field access (stored as Map)
  - Indexing: negative indices on List (Python-style) are supported: xs[0-1] selects the last element. Full slice syntax (xs[a:b:c]) is planned and will require a grammar update.
  - Functions: definitions and calls, multiple parameters, built-in print(x)
  - Modules/imports: imported df++ modules are merged into the current compilation unit; module-qualified names (Alias.x, Alias.f(...)) resolve to the current class
  - Minimal runtime helpers (dfpp.rt.Rt) for numeric ops, comparisons, truthiness, string concatenation
- Tests
  - Unit tests live under tests/unit/<Category>
  - JUnit harness compiles and runs df++ files, compares stdout to inline EXPECTED values, aggregates results, and writes build/dfpp-tests.txt
  - You can enable categories or individual tests in src/test/java/dfpp/DfppFeatureTests.java via INCLUDE_DIRS and INCLUDE
  - Categories include: Data, Expression, Functions, Pattern, Modules, Types, Collections
  - Some tests assert failures (e.g., missing param types, return mismatch) via EXPECTED_ERROR and are considered passing when the expected exception is thrown


### Recent updates

- Typing
  - Structural support for List<T>, Set<T>, Map<K,V> in the type checker (arity-validated)
  - Inference for list literals (element type unification) and record literals/field access
  - Nested collections supported in annotations and expressions (e.g., List<List<Int>>, List<Set<Int>>, Map<String, List<Int>>)
  - Best‑effort return type inference for nominal shapes (primitives and collections)
- Code generation
  - Nested list indexing is verifier‑safe (CHECKCAST ArrayList before get)
- Tests
  - New Collections test suite; moved list tests under Collections; added nested collections positive/negative tests
  - New Tasks test suite: run('...'), chaining via then, and parallel {...} (sequential in v1)
  - Expression short-circuit tests (&&, ||); Data tests for missing fields, list OOB, and negative indices
  - Functions: added error-path tests (unknown fn, arity mismatch)

- Errors & diagnostics
  - Static: indexing non-indexable values now fails at compile-time (clear type error)
  - Calls: wrong-arity messages now include expected/found counts
  - Runtime: list index OOB message includes the offending index and size; non-list indexing reports the actual type

Known limitations (intentional for now):

- Pattern matching: literals + wildcard only (no records/variants yet)
- No tasks/orchestration/contracts/solver; stdlib is minimal; dfVM/fixtures/quotas not wired
- Type checker is intentionally lightweight; parameter types are required (tests include negative cases). Cross-module return inference works in a permissive mode
- No separate module classes; imports are merged into a single class for minimal v1

---

## Table of Contents

* [Why df++? (Motivation)](#why-df-motivation)
* [Design at a glance](#design-at-a-glance)
* [Hello, df++ (quick examples)](#hello-df-quick-examples)

    * [Tasks + Orchestration](#tasks--orchestration)
    * [Pattern Matching (v1)](#pattern-matching-v1)
    * [Collections (List/Set/Map)](#collections-listsetmap)
    * [Contracts & Static Checks](#contracts--static-checks)
    * [Java Interop (simplified import)](#java-interop-simplified-import)
* [The Sandbox: dfVM (mandatory)](#the-sandbox-dfvm-mandatory)
* [Standard Library (v1 interfaces)](#standard-library-v1-interfaces)
* [Compiler Architecture (ANTLR → ASM)](#compiler-architecture-antlr--asm)
* [Packaging: `dfpkg`](#packaging-dfpkg)
* [Tooling (proposal)](#tooling-proposal)
* [Flowtomic case study (how graphs map to df++)](#flowtomic-case-study-how-graphs-map-to-df)
* [Roadmap](#roadmap)
* [Repository layout (proposed)](#repository-layout-proposed)
* [Build from source (proposal)](#build-from-source-proposal)
* [Contributing](#contributing)
* [License](#license)

---

## Why df++? (Motivation)

**One language, three styles—without the bloat.**

* **OOP, but simple:** values implement **multiple interfaces**; we **favor composition**; there is **no inheritance**.
* **Functional by default:** iteration via pipelines over **List, Set, Map**; **pattern matching** is a first-class expression; purity is encouraged.
* **Declarative tasks:** `task 'name' { pre; act { ... }; pos }` puts guard, effects, and postcondition together; orchestration reads like a plan: `run('A').then('B')` and `parallel { 'A', 'B' }`.

**Secure and reproducible by construction.**

* All programs run inside **dfVM**, a **mandatory** userspace sandbox: **capability-gated I/O**, **deterministic replay** (virtual time/RNG, HTTP fixtures), **resource quotas**, and a **syscall trace** for auditability.

**Pragmatic to implement and deploy.**

* The compiler is **ANTLR → ASM (JDK 21)**. Output is JVM bytecode.
* Easy interop with Java libraries, but still capability-checked by dfVM.
* **`dfpkg` bundles** replace many container use cases with faster startup and less operational overhead.

---

## Design at a glance

* **Keywords (8):** `module, import, type, fn, task, const, let, mut`
* **Identifiers:** normal (`snakeCase`) or **back-ticked** with spaces: `` `foo bar` ``
* **Types:** records, ADTs (e.g. `Option`, `Result`), first-order generics (List/Set/Map)
* **Pattern matching (v1):** `match (e) { Pattern -> expr; ... }`
* **Tasks:** `task 'name' { pre P; act { ... }; pos Q }`
* **Orchestration:** `run('A').then('B')`, `parallel { 'A', 'B', ... }`
* **Contracts:** `pre/pos` are pure boolean formulas; optional invariants on pipelines (debug)
* **Static checks:** a lightweight solver discharges simple contracts ahead of time; runtime checks always remain
* **Type inference:** local; explicit top-level `fn` signatures recommended
* **Interop:** direct typed import of JVM classes (proposal), capability-gated at runtime
* **Runtime:** **dfVM** is required in all environments

---

## Hello, df++ (quick examples)

> All code blocks start with a `::df++` marker to signal df++ source.

### Tasks + Orchestration

```text
::df++

module demo.tasks
import std.io as IO

mut counter: Int = 0

task 'increment' {
  act { counter = counter + 1 }
  pos counter >= 1
}

task 'double' {
  pre counter > 0
  act { counter = counter * 2 }
  pos counter % 2 == 0 && counter > 1
}

fn main(): Unit = {
  run('increment').then('double')
  IO.print("counter=" + counter.str())
}
```

### Pattern Matching (v1)

```text
::df++

module demo.match

type Option<T> = None | Some(T)

fn hello(opt: Option<String>): String =
  match (opt) {
    Some(s) -> "Hello, " + s
    None    -> "Hello"
  }
```

### Collections (List/Set/Map)

```text
::df++

module demo.collections

type User = { id: Int, name: String, tags: Set<String> }

fn summarize(us: List<User>): Map<String, Int> = {
  // group by first letter, score tag sets, sum per group
  us.groupBy(u -> u.name.toLower().take(1))       // Map<String, List<User>>
    .mapValues(lst -> lst
      .map(u -> (u.tags.contains("pro") ? 10
                 : (u.tags.contains("new") ? 1 : 0)))
      .fold(0, (acc, s) -> acc + s))
}
```



### Python-style list comprehensions (v1)

You can build lists using a compact Python-like syntax:

```text
::df++

fn demo(): Unit = {
  let xs = [1,2,3,4,5]
  // map only
  print([x * x for x in xs])              // => [1, 4, 9, 16, 25]
  // filter with guard
  print([x for x in xs if x % 2 == 1])    // => [1, 3, 5]
}
```

Notes:

- Current v1 supports a single generator with an optional `if` guard.
- The source (`in` right-hand side) must be a `List<T>`; element type `T` binds the loop variable.
- The result type is `List<U>`, where `U` is the type of the element expression.

### Tuple types (types only in v1)

Tuple types can be written using parentheses, e.g. `(Int, String, Bool)`. They are accepted in type annotations and propagate through the type checker.

```text
::df++

fn usePair(p: (Int, String)): Unit = {
  // …
}
```

Limitations in v1:

- Tuple literals and destructuring are not implemented yet (types only). You cannot construct a tuple value at runtime yet.
- Pattern matching over tuple shapes is not available in v1.

### Unary minus

Unary numeric negation is supported (e.g., `-x`, `-1`).

### Contracts & Static Checks

```text
::df++

module demo.contracts

fn safeHead<T>(xs: List<T>): T =
  match (xs.size()) {
    0 -> (assert(false); xs.head)      // will not happen if solver proves size>0
    _ -> xs.take(1).head
  }

task 'use head' {
  pre [1,2,3].nonEmpty()
  act { let x = safeHead([1,2,3]); /* ... */ }
  pos true
}
```

> The static solver proves the `pre` ensures non-empty; runtime still checks (`assert`) for defense.

### Java Interop (simplified import)

```text
::df++

module demo.jvm
import java: com.example.Http as Http    // proposal: direct JVM import
import std.io as IO

fn fetch(url: String): String = {
  // dfVM must grant 'net' capability and allowlist this symbol
  let bytes: Bytes = Http.get(url)       // signature mapped from JVM classfile
  bytes.utf8()
}
```

---

## The Sandbox: dfVM (mandatory)

**dfVM** enforces safety and reproducibility:

* **Capabilities:** `env`, `fs` (virtual root, path policies), `net` (host/port ACL + HTTP fixtures), `time` (virtual clock), `rng` (seeded PRNG), `conc` (virtual threads).
* **Determinism:** virtual time/RNG; HTTP fixtures record/replay by `(method, URL, headers, body)` key.
* **Quotas:** CPU wallclock, heap soft cap, egress bytes, open FDs, concurrent tasks.
* **Trace:** every effect creates a span (name, timing, sizes, contract verdicts).
* **Deny by default:** reflection, `Unsafe`, raw sockets, `ProcessBuilder`, access outside the virtual FS root.
* **Interop mediation:** even direct Java calls are **manifest-allowlisted** and capability-gated.

---

## Standard Library (v1 interfaces)

*All I/O is implemented via dfVM services.*

### `std.core`

* **ADTs**

    * `type Option<T> = None | Some(T)`
    * `type Result<T,E> = Ok(T) | Err(E)`
* **Futures**

    * `trait Future<T> { map<U>((T)->U): Future<U>; then<U>((T)->Future<U>): Future<U>; await(): T }`
* **List<T>**

    * `map, flatMap, filter, fold, reduce, scan, zip, take, drop, groupBy, sortBy, forEach, size, isEmpty, nonEmpty`
    * `static range(Int, Int): List<Int>`
* **Set<T>**

    * `union, intersect, diff, contains, size, isEmpty, map, filter, toList`
* **Map<K,V>**

    * `get(K): Option<V>, put(K,V): Map<K,V>, remove(K): Map<K,V>`
    * `containsKey, keys(): Set<K>, values(): List<V>, entries(): List<(K,V)>`
    * `mapValues<U>((V)->U): Map<K,U>, filterKeys((K)->Bool): Map<K,V>`
* **Pattern matching helpers**

    * `Option.match<U>(some:(T)->U, none:()->U): U`
    * `Result.match<U>(ok:(T)->U, err:(E)->U): U`
* **Base types**

    * all expose `str(): String`

### `std.json`

* `type Json` (opaque)
* `parse(String): Result<Json,String>`
* `stringify(Json): String`
* `encode<T>(T): Json` (derive for records/ADTs)
* `decode<T>(Json): Result<T,String>`

### `std.io`

* `print(String): Unit`

### `std.sys.*` (via dfVM)

* **env:** `env(String): Option<String>`
* **time:** `nowMs(): Long`, `sleep(Long): Unit`
* **rng:** `randBytes(Int): Bytes`
* **net:**

    * `type HttpResp = { status: Int, headers: Map<String,String>, body: Bytes }`
    * `httpGet(String, Map<String,String>): HttpResp`
    * `httpPost(String, Map<String,String>, Bytes): HttpResp`
* **conc:** `spawn(()->Unit): Future<Unit>`

### `std.flow.orch` (orchestration helpers)

* `type TaskChain = { then(String): TaskChain, await(): Result<Unit,String> }`
* `run(String): TaskChain`
* `parallel(List<String>): Result<Unit,String>`

### `std.contracts`

* `assert(Bool): Unit`
* pipelines accept `.inv(Bool)` in debug builds

---

## Compiler Architecture (ANTLR → ASM)

**Pipeline**

1. **Parse (ANTLR4)** → AST (Java 21 records with source spans)
2. **Typing** → nominal types, interfaces, first-order generics, local inference, non-null after guards, `match` exhaustiveness (finite ADTs exact, otherwise warn)
3. **Lowering** → expression IR, closure conversion, pipeline fusion, tail-call → loops, `match` → decision DAG with binders
4. **Static contract solver** → interval arithmetic + collection cardinalities + SAT on propositional core (see below)
5. **Bytecode (ASM)** → single module class, static fields for globals, `fn`/`task` methods, `COMPUTE_FRAMES`, lines/locals

**Bytecode shape (sketch)**

* Module: `pkg/Module.class`
* Functions: `public static Object f(Object[])`
* Tasks: `t$pre():Z`, `t$act():Object`, `t$pos():Z`

**Lowering example**

Source:

```text
fn add(a: Int, b: Int): Int = a + b
```

Emitted (pseudo-ASM):

```text
mv = cw.visitMethod(ACC_PUBLIC|ACC_STATIC, "add$O",
  "([Ljava/lang/Object;)Ljava/lang/Object;", null, null);
mv.visitCode();
 // load a
mv.visitVarInsn(ALOAD, 0); mv.visitInsn(ICONST_0); mv.visitInsn(AALOAD);
mv.visitTypeInsn(CHECKCAST, "java/lang/Integer");
mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Integer", "intValue", "()I", false);
 // load b
mv.visitVarInsn(ALOAD, 0); mv.visitInsn(ICONST_1); mv.visitInsn(AALOAD);
mv.visitTypeInsn(CHECKCAST, "java/lang/Integer");
mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Integer", "intValue", "()I", false);
 // a + b
mv.visitInsn(IADD);
 // box and return
mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
mv.visitInsn(ARETURN);
mv.visitMaxs(0,0); mv.visitEnd();
```

**Pattern matching lowering**

```text
match (opt) { Some(x) -> f(x); None -> g() }
```

* Generate a tag switch on the ADT variant; on `Some`, extract slot 0, bind `x`, call `f`.

**Static contract solver (v1)**

* **Input:** propositional formulas over:

    * linear integer relations (`a <= b`, `a + c <= k`, etc.)
    * collection cardinalities: `size(xs)`, `isEmpty`, `subset` (when statically derivable)
    * (in)equality over literals and finite ADT tags
* **Flow:** normalize → constant fold → **interval** analysis (Ints/Floats) → **cardinality** bounds (List/Set/Map) → residual CNF → **SAT** (DPLL) with simple learned clauses from conflicts.
* **Verdicts:** `Proved` (can elide runtime check), `Refuted` (counterexample; optionally a compile error), `Unknown` (keep runtime check).
* **Roadmap:** SMT backend for quantified finite checks; LTL monitor synthesis for temporal specs.

---

## Packaging: `dfpkg`

A signed, capability-declaring bundle. Structure (zip):

```
myflow.dfpkg/
  |- module.jar
  |- dfpkg.json
  |- fixtures/           # optional HTTP fixtures for replay
  |- bundle.sig
```

Manifest example:

```json
{
  "entry": "example.flow.Main.main",
  "capabilities": {
    "net": { "hosts": ["api.example.org:443"] },
    "env": ["API_KEY"],
    "time": { "deterministic": true },
    "rng": { "seed": 42 },
    "fs": { "root": "/work", "writable": ["/work/out"] }
  },
  "jvmAllowlist": [
    "com.vendor.sdk.Client#get(Ljava/lang/String;)Ljava/lang/String;"
  ],
  "fixtures": "fixtures/",
  "hashes": { "jar": "sha256:...", "fixtures": "sha256:..." },
  "signature": "ed25519:..."
}
```

The dfVM loader verifies signature, hashes, capabilities, and the Java allowlist before execution.

---

## Tooling (proposal)

* **CLI**

    * `dfpp build` → compiles to a signed `.dfpkg`
    * `dfpp run` → runs a dfpkg under dfVM
    * `dfpp test` → discovers and runs `test 'name' { ... }` blocks (tooling only)
    * `dfpp trace` → streams syscall spans
    * `dfpp replay` → deterministic re-run using fixtures

* **LSP**

    * Hover types (post-inference), go-to-def, rename
    * Contract diagnostics (solver results), quick-fixes for missing capability declarations

* **Debugger**

    * Step through tasks; inspect contracts; view dfVM caps; tail spans

* **Interop helpers**

    * `dfpp jgen` → generate Java stubs for exported df++ fns and df++ externs for JVM classes

> Tooling is part of this proposal; concrete commands may evolve during implementation.

---

## Flowtomic case study (how graphs map to df++)

* **Nodes → tasks:** each node becomes `task 'node name' { pre; act { ... }; pos }`
* **Edges → orchestration:**

    * linear edges → `run('A').then('B')`
    * fan-out → `parallel { 'A', 'B', 'C' }`
* **Shared transforms → pure `fn`s**, and data reshaping via `List/Set/Map` pipelines
* **External code (TS/Python) v1:** run as sidecar services; invoke via `std.sys.net.http*` (fixtures recorded for replay)
* **Ops flow:** export → `dfpp build` → deploy `.dfpkg` → dfVM enforces caps/tracing → CI runs `dfpp replay` for bit-exact runs

Why it fits:

* Graph authoring aligns with **tasks + orchestration**
* Data wrangling matches **functional collections**
* Production needs (least-privilege, determinism, audit) → **dfVM** out of the box

---

## Roadmap

* **v1**

    * Language: pattern matching; records/ADTs; first-order generics; interfaces; tasks; contracts; local type inference
    * Stdlib: core collections, json, io, sys(env/time/rng/net/conc), flow.orch, contracts
    * Compiler: ANTLR → ASM; static solver (interval+cardinality+SAT); line/locals for debug
    * Runtime: dfVM mandatory; fixtures; quotas; tracing
    * Packaging: `dfpkg` + signature

* **v1.1**

    * Better `match` diagnostics; totality checks over user enums
    * More collection ops; richer `Map`/`Set` builders
    * Gradual typing experiments (opt-in)

* **v2**

    * SMT backend; finite quantifiers; simple temporal monitors
    * GraalVM plugin or in-VM JS/Python sandboxes
    * Package registry; dfpkg provenance/attestations

---

## Repository layout (proposed)

```
/compiler/
  /grammar/         # ANTLR *.g4
  /frontend/        # AST, typing, inference, match exhaustiveness
  /lowering/        # IR, closures, fusion
  /solver/          # interval/cardinality/SAT
  /backend/         # ASM emitter
/dfvm/              # sandbox runtime, loader, fixture layer
/stdlib/            # std.core, std.json, std.sys.*, std.flow.orch, std.contracts
/tooling/           # CLI, LSP, test runner, dfpkg signer/loader
/docs/
  README.md         # this file
  SPEC.md           # cut-down spec
  GRAMMAR.md        # ANTLR + abstract grammar notes
  SEMANTICS.md      # typing & dynamic semantics
  FLOWTOMIC.md      # case study details
/examples/          # runnable samples
/tests/             # unit/e2e tests (including 'test' blocks)
```

---

## Build from source (proposal)

Requirements:

* JDK 21
* ANTLR 4.x
* ASM 9.x

Suggested steps (Gradle example):

```bash
# generate parser
./gradlew :compiler:grammar:generateGrammarSource

# build compiler + stdlib + dfvm
./gradlew build

# compile a df++ file to dfpkg
./gradlew :tooling:cli:run --args="build examples/hello.dfpp -o out/hello.dfpkg"

# run under dfVM
./gradlew :tooling:cli:run --args="run out/hello.dfpkg"
```

> Maven builds would mirror these tasks; concrete scripts will be added during implementation.


## Running tests (POC)

- Tests live under tests/unit/<Category>
- Enable categories or individual tests in src/test/java/dfpp/DfppFeatureTests.java:
  - INCLUDE_DIRS = List.of("Expression", "Data", "Pattern", "Functions", "Modules", "Types")
  - INCLUDE = List.of("add", "rec_get", ...)
- Run all enabled tests:

```bash
./gradlew test
```

- The harness aggregates results and writes a log to build/dfpp-tests.txt. Some tests are expected to fail with specific exceptions (e.g., TypeException); these are marked in EXPECTED_ERROR and considered passing when the expected error occurs.


---

## Contributing

* File issues with “spec” or “impl” labels.
* PRs should include tests and, where relevant, spec updates.
* Please keep the **keyword set small**, prefer **composition over inheritance**, and ensure any I/O path is **capability-checked** by dfVM.

---

## License

* Language specification and docs: **CC BY 4.0** (proposed)
* Reference implementation (compiler, dfVM, stdlib, tooling): **Apache 2.0** (proposed)

---

### Appendix: Keyword cheat-sheet

| Category | Keywords                                       |
| -------- | ---------------------------------------------- |
| Modules  | `module`, `import`                             |
| Types    | `type`                                         |
| Code     | `fn`                                           |
| Tasks    | `task`, `pre`, `act`, `pos`, `run`, `parallel` |
| Bindings | `const`, `let`, `mut`                          |

*(Note: `test 'name' { ... }` is a **tooling** construct — recognized by the test runner, ignored by codegen.)*

---

### Appendix: Mini example with `dfpkg` manifest

```text
::df++

module example.flow
import std.io as IO
import std.sys.net as Net
import std.json as Json

type Post = { id: Int, title: String }

mut posts: List<Post> = []

task 'fetch posts' {
  act {
    let r = Net.httpGet("https://api.example.org/posts", {})
    posts = Json.decode<List<Post>>(Json.parse(r.body.utf8()).getOrElse("{}"))
              .getOrElse([])
  }
  pos posts.size() >= 0
}

fn main(): Unit = run('fetch posts').await()
```

`dfpkg.json` (sketch):

```json
{
  "entry": "example.flow.Main.main",
  "capabilities": {
    "net": { "hosts": ["api.example.org:443"] },
    "time": { "deterministic": true },
    "rng": { "seed": 7 }
  },
  "jvmAllowlist": [],
  "hashes": { "jar": "sha256:..." },
  "signature": "ed25519:..."
}
```

---
