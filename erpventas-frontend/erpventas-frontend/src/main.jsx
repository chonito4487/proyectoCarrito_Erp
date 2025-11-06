import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import {ProductsApp} from './ProductsApp.jsx'

createRoot(document.getElementById('root')).render(
  //<StrictMode> // No debe estar en produccion ya que hace doble renderizado
    <ProductsApp title={'Hola Mundo React!'}/>
 // </StrictMode>,
)
