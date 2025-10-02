/** @type {import('tailwindcss').Config} */
export default {
  content: [
    './index.html',
    './src/**/*.{ts,tsx}',
  ],
  theme: {
    extend: {
      colors: {
        brand: {
          50: '#f0f7ff',
          100: '#dff0ff',
          200: '#bfe1ff',
          300: '#99cdff',
          400: '#6bb2ff',
          500: '#3f95ff',
          600: '#2179f3',
          700: '#1a61cb',
          800: '#164ea3',
          900: '#123e82',
        },
      },
      boxShadow: {
        soft: '0 10px 25px rgba(0,0,0,0.06)',
      },
    },
  },
  plugins: [],
}
