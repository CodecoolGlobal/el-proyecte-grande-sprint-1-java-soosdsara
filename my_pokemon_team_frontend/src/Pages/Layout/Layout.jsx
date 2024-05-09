import { Outlet, NavLink } from "react-router-dom";

const Layout = () => {
  return (
    <div className="Layout">
      <nav>
        <NavLink to={`/userpage`}> My Pokemon Team</NavLink>
        <NavLink to={`/userpage/search`}> Find a new pokemon</NavLink>
      </nav>
      <Outlet />
    </div>
  );
};

export default Layout;

