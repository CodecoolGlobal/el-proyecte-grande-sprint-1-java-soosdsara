import { Outlet, NavLink } from "react-router-dom";

const Layout = () => (
  <div className="Layout">
    <nav>
      <NavLink to="/userpage/1"> My Pokemon Team</NavLink>
      <NavLink to="/userpage/1/search"> Find a new pokemon</NavLink>
    </nav>
    <Outlet />
  </div>
);

export default Layout;
