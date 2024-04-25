import React from 'react'
import ReactDOM from 'react-dom/client'
import Login from './Pages/Login.jsx'
import './index.css'
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Registration from './Pages/Registration.jsx';

const router = createBrowserRouter([
  {
    path: "/",
    element : <Login />
  },
  
  {
    path: "/registration",
    element: <Registration />
    }

])

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <RouterProvider router = {router}/>
  </React.StrictMode>,
)
