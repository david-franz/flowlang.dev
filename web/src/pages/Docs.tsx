import { useMemo, useState } from 'react'
import CodeBlock from '../components/CodeBlock'
import { sections } from '../content/docsSections'

export default function Docs() {
  const [active, setActive] = useState(sections[0].id)
  const current = useMemo(() => sections.find(s => s.id === active)!, [active])

  return (
    <div className="mx-auto container-page px-4 py-8 grid md:grid-cols-[260px_1fr] gap-8">
      <aside className="md:sticky md:top-16 h-max">
        <div className="text-sm font-medium text-slate-500 mb-2">Docs</div>
        <nav className="space-y-1">
          {sections.map(s => (
            <button
              key={s.id}
              onClick={() => setActive(s.id)}
              className={`block w-full text-left px-3 py-2 rounded-md text-sm ${active===s.id? 'bg-slate-100 text-slate-900' : 'text-slate-600 hover:bg-slate-50 hover:text-slate-900'}`}
            >
              {s.title}
            </button>
          ))}
        </nav>
      </aside>
      <article>
        <h1 className="text-2xl font-semibold">{current.title}</h1>
        {current.blurb && (
          <p className="mt-2 text-slate-600 max-w-prose">{current.blurb}</p>
        )}
        {current.code && (
          <div className="mt-6"><CodeBlock code={current.code} height={260} /></div>
        )}
        <div className="mt-8 text-sm text-slate-500">Use the sidebar to explore each feature. Examples are read‑only.</div>
      </article>
    </div>
  )
}
