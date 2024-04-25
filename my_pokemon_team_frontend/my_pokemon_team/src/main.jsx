import React from 'react'
import ReactDOM from 'react-dom/client'
import Login from './Pages/Login.jsx'
import './index.css'
import UserPage from './Pages/UserPage.jsx'
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import Registration from './Pages/Registration.jsx';

const router = createBrowserRouter([
    {
        path: "/",
        element: <Login/>
    },
    {
        path: "/registration",
        element: <Registration/>
    },
    {
        path: "/userpage/:id",
        element: <UserPage/>
    },
])

ReactDOM.createRoot(document.getElementById('root')).render(
    <React.StrictMode>
        <RouterProvider router={router}/>
    </React.StrictMode>,
)
