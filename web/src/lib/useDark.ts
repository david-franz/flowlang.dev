import { useEffect, useState } from 'react'

export function useDark(): [boolean, () => void] {
  const [dark, setDark] = useState<boolean>(() => {
    if (typeof window === 'undefined') return false
    const saved = localStorage.getItem('dfpp:dark')
    if (saved != null) return saved === '1'
    return window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches
  })

  useEffect(() => {
    const el = document.documentElement
    if (dark) el.classList.add('dark')
    else el.classList.remove('dark')
    localStorage.setItem('dfpp:dark', dark ? '1' : '0')
  }, [dark])

  const toggle = () => setDark(d => !d)
  return [dark, toggle]
}
