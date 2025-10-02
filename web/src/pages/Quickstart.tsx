export default function Quickstart() {
  return (
    <section className="mx-auto container-page px-4 py-12">
      <h1 className="text-3xl font-semibold">Quickstart</h1>
      <ol className="mt-6 space-y-4 text-slate-700">
        <li>
          <strong>Build</strong>
          <pre className="mt-2 rounded-md bg-slate-50 p-3 border border-slate-200 overflow-x-auto text-sm">./gradlew clean build</pre>
        </li>
        <li>
          <strong>Run an example</strong>
          <pre className="mt-2 rounded-md bg-slate-50 p-3 border border-slate-200 overflow-x-auto text-sm">./gradlew run --args="examples/hello_world.dfpp demo.hello"</pre>
        </li>
        <li>
          <strong>Run tests</strong>
          <pre className="mt-2 rounded-md bg-slate-50 p-3 border border-slate-200 overflow-x-auto text-sm">./gradlew test</pre>
        </li>
      </ol>
      <p className="mt-6 text-slate-600">See the README in the repo for details and the design doc (df++_design_doc.pdf) for the broader model.</p>
    </section>
  )
}
