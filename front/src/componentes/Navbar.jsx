import React from 'react'
import { Link, NavLink } from 'react-router-dom'

export const Navbar = () => {

    return (
        <nav className="navbar navbar-expand-sm navbar-dark bg-dark p-2">
            
            <Link
                className="navbar-brand" 
                to="/"
            >
                HOME PAGE
            </Link>

            <div className="navbar-collapse">
                <div className="navbar-nav">

                    <NavLink 
                        className="nav-item nav-link" 
                        to="/user"
                    >
                        USER PAGE
                    </NavLink>

                    <NavLink 
                        className="nav-item nav-link" 
                        to="/admin"
                    >
                        ADMIN PAGE
                    </NavLink>
                   
                    
                </div>
            </div>

        </nav>
    )
}
/*
 <NavLink 
                        className="nav-item nav-link" 
                        to="/buscarunidad"
                    >
                        "OPERAR SOBRE UNA UNIDAD"
                    </NavLink>
*/ 