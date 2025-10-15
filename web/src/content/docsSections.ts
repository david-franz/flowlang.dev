export type DocSection = {
  id: string
  title: string
  blurb?: string
  code?: string
}

export const sections: DocSection[] = [
  {
    id: 'modules',
    title: 'Modules & Imports',
    blurb: 'Simple modules and aliasing. Imports are merged in v1.',
    code: `module demo.hello
import lib.util as U

fn main() { print("Hello, flowlang!") }`
  },
  {
    id: 'bindings',
    title: 'Bindings',
    blurb: 'const and let at top-level or inside blocks.',
    code: `const n: Int = 5
let msg = "hi"`
  },
  {
    id: 'types',
    title: 'Types & Inference',
    blurb: 'Primitives, records, first‑order generics in annotations, and local inference.',
    code: `type User = { id: Int, name: String }

fn greet(u: User): String = "hi " + u.name`
  },
  {
    id: 'records',
    title: 'Records',
    code: `let u = { id = 1, name = "ada" }
print(u.name)`
  },
  {
    id: 'collections',
    title: 'Collections',
    blurb: 'List literals, indexing, negative indices, maps/sets in annotations.',
    code: `let xs = [1,2,3,4]
print(xs[0])
print(xs[-1])`
  },
  {
    id: 'slicing',
    title: 'Slicing',
    code: `let xs = [1,2,3,4,5]
print(xs[1:4])     // [2,3,4]
print(xs[-3:])     // [3,4,5]
print(xs[4:0:-2])  // [5,3]`
  },
  {
    id: 'comp',
    title: 'Comprehensions',
    code: `let xs = [1,2,3,4,5]
print([x * x for x in xs if x % 2 == 1])`
  },
  {
    id: 'expr',
    title: 'Expressions',
    blurb: 'Arithmetic, comparisons, boolean ops (short‑circuit), ternary.',
    code: `print(2 + 3)
print(1 < 2 && 3 >= 3)
print(true ? "t" : "f")`
  },
  {
    id: 'unary',
    title: 'Unary minus',
    code: `let x = 5
print(-x)`
  },
  {
    id: 'functions',
    title: 'Functions',
    code: `fn add(a: Int, b: Int): Int = a + b

fn main() { print(add(3,4)) }`
  },
  {
    id: 'match',
    title: 'Pattern Matching (v1)',
    blurb: 'Literals and wildcard.',
    code: `fn pick(b: Bool): String =
  match (b) { true -> "T", _ -> "F" }`
  },
  {
    id: 'tasks',
    title: 'Tasks & Orchestration',
    blurb: 'run, then, and parallel (sequential in v1).',
    code: `task 'A' { act { print("A") } }
task 'B' { act { print("B") } }

fn main() {
  run('A').then('B')
  parallel { 'A', 'B' }
}`
  },
  {
    id: 'tuples',
    title: 'Tuple types (annotations)',
    blurb: 'Type annotations support (Int, String), value tuples are not in v1.',
    code: `fn usePair(p: (Int, String)): Unit = { }`
  }
]
