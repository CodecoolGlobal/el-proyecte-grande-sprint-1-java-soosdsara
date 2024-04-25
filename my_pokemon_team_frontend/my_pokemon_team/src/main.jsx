import React from "react";
import ReactDOM from "react-dom/client";
import Login from "./Pages/Login.jsx";
import "./index.css";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Search from "./Pages/Search.jsx";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Login />,
  },
  {
    path: "/:id/search",
    element: <Search />,
  },
]);

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
