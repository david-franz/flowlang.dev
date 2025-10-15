export default function Quickstart() {
  return (
    <section className="mx-auto container-page px-4 py-12">
      <h1 className="text-3xl font-semibold text-slate-900 dark:text-white">Quickstart</h1>
      <ol className="mt-6 space-y-4 text-slate-700 dark:text-slate-200">
        <li>
          <strong>Build</strong>
          <pre className="mt-2 rounded-md bg-slate-50 dark:bg-slate-900 p-3 border border-slate-200 dark:border-slate-800 overflow-x-auto text-sm text-slate-800 dark:text-slate-200">./gradlew clean build</pre>
        </li>
        <li>
          <strong>Run an example</strong>
          <pre className="mt-2 rounded-md bg-slate-50 dark:bg-slate-900 p-3 border border-slate-200 dark:border-slate-800 overflow-x-auto text-sm text-slate-800 dark:text-slate-200">./gradlew run --args="examples/hello_world.dfpp demo.hello"</pre>
        </li>
        <li>
          <strong>Run tests</strong>
          <pre className="mt-2 rounded-md bg-slate-50 dark:bg-slate-900 p-3 border border-slate-200 dark:border-slate-800 overflow-x-auto text-sm text-slate-800 dark:text-slate-200">./gradlew test</pre>
        </li>
      </ol>
      <p className="mt-6 text-slate-600 dark:text-slate-300">See the README in the repo for details and the design doc (flowlang_design_doc.pdf) for the broader model.</p>
    </section>
  )
}
