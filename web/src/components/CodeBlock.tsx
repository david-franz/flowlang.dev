import Editor from '@monaco-editor/react'

type Props = {
  code: string
  height?: number | string
  readOnly?: boolean
  className?: string
}

export default function CodeBlock({ code, height = 220, readOnly = true, className }: Props) {
  return (
    <div className={`rounded-lg overflow-hidden border border-slate-200 code-shadow ${className ?? ''}`}>
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
        theme="vs"
        beforeMount={(monaco) => {
          // simple dfpp language registration
          if (!(monaco as any).languages.getLanguages().some((l:any)=>l.id==='dfpp')) {
            monaco.languages.register({ id: 'dfpp' })
            monaco.languages.setMonarchTokensProvider('dfpp', {
              keywords: [
                'module','import','as','type','fn','task','pre','act','pos','run','parallel','match','const','let','mut','true','false','null','for','in','if'
              ],
              tokenizer: {
                root: [
                  [/\/[\/].*$/, 'comment'],
                  [/"([^\\"]|\\.)*"|'([^\\']|\\.)*'/, 'string'],
                  [/\b\d+\b/, 'number'],
                  [/\b(?:module|import|as|type|fn|task|pre|act|pos|run|parallel|match|const|let|mut|true|false|null|for|in|if)\b/, 'keyword'],
                  [/[{}()\[\]]/, '@brackets'],
                  [/[a-zA-Z_][a-zA-Z0-9_\-]*/, 'identifier'],
                ]
              }
            } as any)
          }
        }}
      />
    </div>
  )
}
