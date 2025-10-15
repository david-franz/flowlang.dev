import { useEffect, useState } from 'react'

export function readDarkPreference(): boolean {
  if (typeof document === 'undefined') return false

  if (document.documentElement.classList.contains('dark')) return true

  if (typeof window === 'undefined') return false

  try {
    const saved = window.localStorage.getItem('flowlang:dark')
    if (saved != null) return saved === '1'
  } catch {}

  return !!(window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches)
}

export function useDark(): [boolean, () => void] {
  const [dark, setDark] = useState<boolean>(() => readDarkPreference())

  useEffect(() => {
    const el = document.documentElement
    if (dark) el.classList.add('dark')
    else el.classList.remove('dark')
    try {
      window.localStorage.setItem('flowlang:dark', dark ? '1' : '0')
    } catch {}
    try {
      window.dispatchEvent(new CustomEvent('flowlang:theme', { detail: { dark } }))
    } catch {}
  }, [dark])

  const toggle = () => setDark(d => !d)
  return [dark, toggle]
}
