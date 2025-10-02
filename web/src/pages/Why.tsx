export default function Why() {
  return (
    <section className="mx-auto container-page px-4 py-12">
      <h1 className="text-3xl font-semibold text-slate-900 dark:text-white">Why df++</h1>
      <div className="mt-4 grid gap-8 md:grid-cols-2">
        <Point
          title="Practical PL design"
          body="Type inference where it helps, explicit types where it counts. Structural records. First‑order polymorphism for List/Set/Map in annotations."
        />
        <Point
          title="Predictable on the JVM"
          body="ANTLR → AST → type check → ASM. Verifier‑safe bytecode, tiny runtime helpers. Easy to audit, easy to extend."
        />
        <Point
          title="Declarative orchestration"
          body="Tasks with pre/act/pos and a small set of combinators (run, then, parallel). Compose plans without ceremony."
        />
        <Point
          title="Concise data work"
          body="Records, list literals, comprehensions, indexing with negatives, and slices. Gets out of your way."
        />
      </div>
    </section>
  )
}

function Point({ title, body }: { title: string, body: string }) {
  return (
    <div className="rounded-xl border border-slate-200 dark:border-slate-800 p-6 bg-white dark:bg-slate-900/50">
      <div className="text-slate-900 dark:text-white font-medium">{title}</div>
      <p className="mt-2 text-slate-600 dark:text-slate-300 text-sm leading-6">{body}</p>
    </div>
  )
}
