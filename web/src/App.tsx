import { Route, Routes, NavLink, Link } from 'react-router-dom'
import { useDark } from './lib/useDark'
import Home from './pages/Home'
import Why from './pages/Why'
import Quickstart from './pages/Quickstart'
import Docs from './pages/Docs'
import REPL from './pages/REPL'

function Navbar() {
  const [dark, toggleDark] = useDark()
  const linkCls = ({ isActive }: {isActive: boolean}) =>
    `px-3 py-2 rounded-md text-sm font-medium ${isActive ? 'text-brand-700 dark:text-brand-400' : 'text-slate-600 dark:text-slate-300 hover:text-slate-900 dark:hover:text-white'}`
  return (
    <header className="sticky top-0 z-40 bg-white/80 dark:bg-slate-950/70 backdrop-blur border-b border-slate-200 dark:border-slate-800">
      <div className="mx-auto container-page px-4 flex h-14 items-center justify-between">
        <Link to="/" className="flex items-center gap-2 text-slate-900 dark:text-white font-semibold">
          <span className="inline-block w-2.5 h-2.5 rounded-full bg-brand-600"></span>
          df++
        </Link>
        <nav className="flex items-center gap-1">
          <NavLink to="/why" className={linkCls}>Why</NavLink>
          <NavLink to="/quickstart" className={linkCls}>Quickstart</NavLink>
          <NavLink to="/docs" className={linkCls}>Docs</NavLink>
          <NavLink to="/repl" className={linkCls}>REPL</NavLink>
          <button onClick={toggleDark} className="ml-2 px-3 py-2 text-sm rounded-md border border-slate-300 dark:border-slate-700 text-slate-700 dark:text-slate-200 hover:bg-slate-50 dark:hover:bg-slate-900">{dark ? 'Light' : 'Dark'}</button>
        </nav>
      </div>
    </header>
  )
}

function Footer() {
  return (
    <footer className="mt-20 border-t border-slate-200 dark:border-slate-800">
      <div className="mx-auto container-page px-4 py-8 text-sm text-slate-500 dark:text-slate-400 flex flex-col md:flex-row items-center justify-between gap-3">
        <div>© {new Date().getFullYear()} df++</div>
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
