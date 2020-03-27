import React from "react";
import { NavLink } from "react-router-dom";

const Header = () => (
  <header className="header">

    <nav className="header-nav center absolute top-0 left-0 right-0 bottom-0 clearfix h6 white">
      <NavLink
        exact
        to="/"
        className="header-nav-link btn btn-outline btn-small regular caps rounded-left"
        activeClassName="is-active btn-primary bg-white black"
      >
        Atras
      </NavLink>
    </nav>
  </header>
);

export default Header;
