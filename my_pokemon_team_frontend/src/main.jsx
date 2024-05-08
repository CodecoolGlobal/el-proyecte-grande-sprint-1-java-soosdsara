import React from "react";
import Login from "./Pages/Login.jsx";
import ReactDOM from "react-dom/client";
import Search from "./Pages/Search.jsx";
import UserPage from "./Pages/UserPage.jsx";
import Registration from "./Pages/Registration.jsx";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Layout from "./Pages/Layout/Layout.jsx";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Login />,
  },
  {
    path: "/registration",
    element: <Registration />,
  },
  {
    path: "/userpage",
    element: <Layout />,
    children: [
      { path: "/userpage", 
        element: <UserPage /> 
      },
      {
        path: "/userpage/search",
        element: <Search />,
      },
    ],
  },
]);

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
