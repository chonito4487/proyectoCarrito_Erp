import React from 'react';
import { createRoot } from 'react-dom/client'
import App from './App';              // Importamos el componente principal
import "/src/styles/global.css";         // 👈 Importamos los estilos globales


createRoot(document.getElementById('root')).render(
   <React.StrictMode>
    <App />
  </React.StrictMode>
)
