import { Outlet, NavLink } from "react-router-dom";

import "./Layout.css";

const Layout = () => {
  return (
    <div className="Layout">
      <nav>
        <NavLink to={`/userpage`} className={({ isActive }) => isActive? 'active' : ''}>
          <button>My Pokemon Team</button>
        </NavLink>
        <NavLink to={`/userpage/search`} className={({ isActive }) => isActive? 'active' : ''}>
          <button>Find a new pokemon</button>
        </NavLink>
      </nav>
      <Outlet />
    </div>
  );
};

export default Layout;

