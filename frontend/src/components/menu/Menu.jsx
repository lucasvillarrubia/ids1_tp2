import React from 'react'
import { MenuBG, MenuUI, MenuExit } from './MenuStyles'
import { Link } from 'react-router-dom'
import { useSelector, useDispatch } from 'react-redux'
import {logoutUser} from "../../features/users/usersAPI.js";


const Menu = ({ menuDisplay, setMenuDisplay }) => {
        const dispatch = useDispatch();
        const { currentUser } = useSelector(state => state.users);

        const handleLogout = () => {
                dispatch(logoutUser);
                setMenuDisplay(false);
        };

        return (
                <>
                        {(menuDisplay) && 
                                <MenuBG onClick={() => setMenuDisplay(!menuDisplay)}></MenuBG>
                        }
                        {(menuDisplay) && 
                                <MenuUI>
                                        <MenuExit onClick={() => setMenuDisplay(!menuDisplay)}>X</MenuExit>
                                        <Link to={'/'} onClick={() => setMenuDisplay(!menuDisplay)}>Home</Link>
                                        {currentUser ? (
                                            <p onClick={handleLogout} style={{ cursor: "pointer" }}>Cerrar Sesión</p>
                                        ) : (
                                            <Link to="/login" onClick={() => setMenuDisplay(false)}>Iniciar Sesión</Link>
                                        )}
                                        <Link to={'/signup'} onClick={() => setMenuDisplay(!menuDisplay)}>Registrarse</Link>
                                </MenuUI>
                        }
                </>
        )
}

export default Menu