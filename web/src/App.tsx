import { useState } from 'react'
import { Route, Routes, NavLink, Link } from 'react-router-dom'
import { useDark } from './lib/useDark'
import Home from './pages/Home'
import Why from './pages/Why'
import Quickstart from './pages/Quickstart'
import Docs from './pages/Docs'
import REPL from './pages/REPL'

function Navbar() {
  const [dark, toggleDark] = useDark()
  const [menuOpen, setMenuOpen] = useState(false)
  const links = [
    { to: '/why', label: 'Why' },
    { to: '/quickstart', label: 'Quickstart' },
    { to: '/docs', label: 'Docs' },
    { to: '/repl', label: 'REPL' }
  ]
  const linkCls = ({ isActive }: {isActive: boolean}) =>
    `block px-3 py-2 rounded-md text-sm font-medium transition-colors ${
      isActive
        ? 'text-brand-700 dark:text-brand-400'
        : 'text-slate-600 dark:text-slate-300 hover:text-slate-900 dark:hover:text-white'
    }`
  const closeMenu = () => setMenuOpen(false)
  return (
    <header className="sticky top-0 z-40 bg-white/80 dark:bg-slate-950/70 backdrop-blur border-b border-slate-200 dark:border-slate-800">
      <div className="relative mx-auto container-page px-4 flex h-14 items-center justify-between">
        <Link to="/" onClick={closeMenu} className="flex items-center gap-2 text-slate-900 dark:text-white font-semibold">
          <span className="inline-block w-2.5 h-2.5 rounded-full bg-brand-600"></span>
          flowlang
        </Link>
        <nav className="hidden md:flex items-center gap-1">
          {links.map(link => (
            <NavLink key={link.to} to={link.to} className={linkCls} onClick={closeMenu}>
              {link.label}
            </NavLink>
          ))}
          <button
            type="button"
            onClick={toggleDark}
            className="ml-2 px-3 py-2 text-sm rounded-md border border-slate-300 dark:border-slate-700 text-slate-700 dark:text-slate-200 hover:bg-slate-50 dark:hover:bg-slate-900"
          >
            {dark ? 'Light' : 'Dark'}
          </button>
        </nav>
        <div className="md:hidden flex items-center gap-2">
          <button
            type="button"
            onClick={toggleDark}
            className="px-3 py-2 text-sm rounded-md border border-slate-300 dark:border-slate-700 text-slate-700 dark:text-slate-200 hover:bg-slate-50 dark:hover:bg-slate-900"
            aria-label="Toggle color mode"
          >
            {dark ? 'Light' : 'Dark'}
          </button>
          <button
            type="button"
            onClick={() => setMenuOpen(prev => !prev)}
            className="p-2 rounded-md border border-slate-300 dark:border-slate-700 text-slate-700 dark:text-slate-200"
            aria-label="Toggle navigation menu"
            aria-expanded={menuOpen}
          >
            <span className="sr-only">Menu</span>
            <svg className="h-5 w-5" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
              <path fillRule="evenodd" d="M3 5h14a1 1 0 010 2H3a1 1 0 110-2zm0 4h14a1 1 0 010 2H3a1 1 0 110-2zm0 4h14a1 1 0 010 2H3a1 1 0 110-2z" clipRule="evenodd" />
            </svg>
          </button>
        </div>
        {menuOpen && (
          <div className="absolute inset-x-0 top-14 md:hidden border-b border-slate-200 dark:border-slate-800 bg-white/95 dark:bg-slate-950/95 backdrop-blur">
            <nav className="px-4 py-3 flex flex-col gap-1">
              {links.map(link => (
                <NavLink key={link.to} to={link.to} className={linkCls} onClick={closeMenu}>
                  {link.label}
                </NavLink>
              ))}
            </nav>
          </div>
        )}
      </div>
    </header>
  )
}

function Footer() {
  return (
    <footer className="mt-20 border-t border-slate-200 dark:border-slate-800">
      <div className="mx-auto container-page px-4 py-8 text-sm text-slate-500 dark:text-slate-400 flex flex-col md:flex-row items-center justify-between gap-3">
        <div>© {new Date().getFullYear()} flowlang</div>
        <div className="flex gap-4">
          <a className="hover:text-slate-800 dark:hover:text-white" href="https://" target="_blank" rel="noreferrer">GitHub</a>
          <Link className="hover:text-slate-800 dark:hover:text-white" to="/docs">Docs</Link>
          <Link className="hover:text-slate-800 dark:hover:text-white" to="/quickstart">Quickstart</Link>
        </div>
      </div>
    </footer>
  )
}

export default function App() {
  return (
    <div className="min-h-screen flex flex-col">
      <Navbar />
      <main className="flex-1">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/why" element={<Why />} />
          <Route path="/quickstart" element={<Quickstart />} />
          <Route path="/docs" element={<Docs />} />
          <Route path="/repl" element={<REPL />} />
        </Routes>
      </main>
      <Footer />
    </div>
  )
}
