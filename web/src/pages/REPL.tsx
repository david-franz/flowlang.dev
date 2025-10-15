import { useCallback, useMemo, useState } from 'react'
import Editor from '@monaco-editor/react'
import { useThemeSignal } from '../lib/useThemeSignal'
import { runFlowlang } from '../lib/replClient'

const starter = `module demo.repl

fn main() {
  let xs = [1,2,3,4,5]
  print([x * x for x in xs if x % 2 == 1])
  print("Hello, flowlang!")
}`

function registerFlowlang(monaco: any) {
  if (!monaco.languages.getLanguages().some((l: any) => l.id === 'flowlang')) {
    monaco.languages.register({ id: 'flowlang' })
    monaco.languages.setMonarchTokensProvider('flowlang', {
      keywords: [
        'module','import','as','type','fn','task','pre','act','pos','run','parallel','match','const','let','mut','true','false','null','for','in','if'
      ],
      tokenizer: {
        root: [
          [/\/\/.*$/, 'comment'],
          [/"([^\\"]|\\.)*"|'([^\\']|\\.)*'/, 'string'],
          [/\b\d+\b/, 'number'],
          [/\b(?:module|import|as|type|fn|task|pre|act|pos|run|parallel|match|const|let|mut|true|false|null|for|in|if)\b/, 'keyword'],
          [/[{}()\[\]]/, '@brackets'],
          [/[a-zA-Z_][a-zA-Z0-9_\-]*/, 'identifier'],
        ]
      }
    } as any)
  }
}

export default function REPL() {
  const { dark } = useThemeSignal()
  const [code, setCode] = useState(starter)
  const [out, setOut] = useState('')
  const [err, setErr] = useState('')
  const [busy, setBusy] = useState(false)

  const run = useCallback(async () => {
    setBusy(true)
    setErr('')
    setOut('')
    const res = await runFlowlang(code)
    if (res.stderr) setErr(res.stderr)
    setOut(res.stdout ?? '')
    setBusy(false)
  }, [code])

  const buttonCls = useMemo(() => `inline-flex items-center gap-2 px-4 py-2 rounded-md ${busy? 'bg-slate-300' : 'bg-brand-600 hover:bg-brand-700'} text-white`, [busy])

  const beforeMount = (monaco: any) => { registerFlowlang(monaco) }

  return (
    <section className="mx-auto container-page px-4 py-8">
      <h1 className="text-3xl font-semibold">REPL</h1>
      <p className="mt-2 text-slate-600 dark:text-slate-300">Write flowlang code on the left and run it.</p>
      <div className="mt-6 grid md:grid-cols-2 gap-6">
        <div className="rounded-lg overflow-hidden border border-slate-200 dark:border-slate-800 code-shadow">
          <Editor
            height={380}
            language="flowlang"
            value={code}
            onChange={(v)=>setCode(v ?? '')}
            options={{ minimap: { enabled: false }, fontSize: 14, wordWrap: 'on' }}
            theme={dark ? 'vs-dark' : 'vs'}
            beforeMount={beforeMount}
          />
        </div>
        <div className="flex flex-col">
          <div className="flex items-center gap-3">
            <button className={buttonCls} onClick={run} disabled={busy}>
              {busy ? 'Running…' : 'Run'}
            </button>
            <button className="inline-flex items-center px-3 py-2 rounded-md border border-slate-300 dark:border-slate-700 text-slate-800 dark:text-slate-200 hover:bg-slate-50 dark:hover:bg-slate-900" onClick={()=>setCode(starter)}>Reset</button>
          </div>
          
          <div className="mt-4">
            <div className="text-sm font-medium text-slate-700 mb-1">Stdout</div>
            <pre className="rounded-md bg-slate-50 dark:bg-slate-900 p-3 border border-slate-200 dark:border-slate-800 overflow-auto text-sm h-40 text-slate-800 dark:text-slate-200">{out}</pre>
          </div>
          <div className="mt-4">
            <div className="text-sm font-medium text-slate-700 mb-1">Errors</div>
            <pre className="rounded-md bg-slate-50 dark:bg-slate-900 p-3 border border-slate-200 dark:border-slate-800 overflow-auto text-sm h-40 text-rose-600 dark:text-rose-400">{err}</pre>
          </div>
        </div>
      </div>
    </section>
  )
}
