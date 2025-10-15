import { useEffect, useState } from 'react'
import { readDarkPreference } from './useDark'

export function useThemeSignal() {
  const getDark = () => readDarkPreference()
  const [tick, setTick] = useState(0)
  const [dark, setDark] = useState(getDark())

  useEffect(() => {
    const onTheme = (e: Event) => {
      setDark(getDark())
      setTick(t => t + 1)
    }
    window.addEventListener('flowlang:theme', onTheme as any)
    return () => window.removeEventListener('flowlang:theme', onTheme as any)
  }, [])

  return { dark, tick }
}
