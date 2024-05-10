import { Outlet, NavLink, Link } from "react-router-dom";

import "./Layout.css";

const Layout = () => {
  return (
    <div className="Layout">
      <nav>
      <Link to="/userpage">
        <button className="Logo" >My Pokemon Team</button>
        </Link>
        <NavLink to={`/userpage/search`}>
          <button>Find a new pokemon</button>
        </NavLink>
      </nav>
      <Outlet />
    </div>
  );
};

export default Layout;

