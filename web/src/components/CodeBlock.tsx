import Editor from '@monaco-editor/react'
import { registerDfpp } from '../lib/monacoDfpp'

type Props = {
  code: string
  height?: number | string
  readOnly?: boolean
  className?: string
}

export default function CodeBlock({ code, height = 220, readOnly = true, className }: Props) {
  const theme = typeof document !== 'undefined' && document.documentElement.classList.contains('dark') ? 'vs-dark' : 'vs'
  return (
    <div className={`rounded-lg overflow-hidden border border-slate-200 dark:border-slate-800 code-shadow ${className ?? ''}`}>
      <Editor
        height={height}
        defaultLanguage="dfpp"
        value={code}
        options={{
          readOnly,
          minimap: { enabled: false },
          scrollBeyondLastLine: false,
          fontSize: 14,
          wordWrap: 'on',
          lineNumbers: 'off',
          renderLineHighlight: 'none',
          padding: { top: 12, bottom: 12 },
        }}
        theme={theme}
        beforeMount={(monaco) => registerDfpp(monaco)}
      />
    </div>
  )
}
