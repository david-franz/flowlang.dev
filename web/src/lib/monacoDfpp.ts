export function registerDfpp(monaco: any) {
  if (!monaco.languages.getLanguages().some((l: any) => l.id === 'dfpp')) {
    monaco.languages.register({ id: 'dfpp' })
    monaco.languages.setMonarchTokensProvider('dfpp', {
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
