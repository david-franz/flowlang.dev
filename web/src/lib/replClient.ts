export type ReplResult = { stdout: string; stderr?: string }

export async function runDfpp(code: string): Promise<ReplResult> {
  try {
    const resp = await fetch('/api/run', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ code })
    })
    if (!resp.ok) throw new Error(`${resp.status} ${resp.statusText}`)
    return await resp.json()
  } catch (e: any) {
    return {
      stdout: '',
      stderr: 'REPL backend not connected. Implement POST /api/run to compile and run code.\n' + (e?.message ?? String(e))
    }
  }
}
