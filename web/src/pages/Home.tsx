import { Link } from 'react-router-dom'
import CodeBlock from '../components/CodeBlock'

const heroCode = `module demo.hello

fn main() {
  let xs = [1,2,3,4,5]
  print([x * x for x in xs if x % 2 == 1])
  print("Hello, df++!")
}`

export default function Home() {
  return (
    <section className="mx-auto container-page px-4">
      <div className="py-16 md:py-24 grid md:grid-cols-2 gap-10 items-center">
        <div>
          <h1 className="text-4xl md:text-5xl font-semibold tracking-tight text-slate-900 dark:text-white">df++</h1>
          <p className="mt-4 text-lg text-slate-600 dark:text-slate-300 max-w-prose">
            A concise language for the JVM that blends functional collections, lightweight records, and declarative orchestration.
          </p>
          <div className="mt-6 flex gap-3">
            <Link to="/quickstart" className="inline-flex items-center px-4 py-2 rounded-md bg-brand-600 text-white shadow-soft hover:bg-brand-700">
              Quickstart
            </Link>
            <Link to="/docs" className="inline-flex items-center px-4 py-2 rounded-md border border-slate-300 dark:border-slate-700 text-slate-800 dark:text-slate-200 hover:bg-slate-50 dark:hover:bg-slate-900">
              Read the docs
            </Link>
          </div>
          <div className="mt-6 text-sm text-slate-500">Read the design doc in this repo: df++_design_doc.pdf</div>
        </div>
        <div>
          <CodeBlock code={heroCode} height={300} />
        </div>
      </div>

      <div className="grid md:grid-cols-3 gap-6 py-8">
        <Feature title="Type inference" body="Local inference with clear annotations where it matters. Records and first‑order collections are easy to use and reason about." />
        <Feature title="Robust codegen" body="Verifier‑safe JVM bytecode via ASM. Small runtime surface. Behavior is predictable and fast." />
        <Feature title="Declarative tasks" body="Define pre/act/pos and orchestrate with run, then, and parallel. Reads like a plan." />
      </div>
    </section>
  )
}

function Feature({ title, body }: { title: string, body: string }) {
  return (
    <div className="rounded-xl border border-slate-200 dark:border-slate-800 p-5 bg-white dark:bg-slate-900/50">
      <div className="text-slate-900 dark:text-white font-medium">{title}</div>
      <p className="mt-2 text-slate-600 dark:text-slate-300 text-sm leading-6">{body}</p>
    </div>
  )
}
