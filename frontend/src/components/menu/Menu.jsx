import React from 'react'
import { MenuBG, MenuUI, MenuExit } from './MenuStyles'
import { Link } from 'react-router-dom'
import { useSelector, useDispatch } from 'react-redux'
import { logout } from "../../features/users/usersSlice.js";
import { useNavigate } from 'react-router-dom'


const Menu = ({ menuDisplay, setMenuDisplay }) => {
        const dispatch = useDispatch();
        const navigate = useNavigate();
        const { currentUser } = useSelector(state => state.users);

        const handleLogout = () => {
                dispatch(logout());
                setMenuDisplay(false);
                navigate('/');
        };

        const handleMenuClick = () => {
                setMenuDisplay(!menuDisplay);

        }

        return (
                <>
                        {(menuDisplay) && 
                                <MenuBG onClick={() => setMenuDisplay(!menuDisplay)}></MenuBG>
                        }
                        {(menuDisplay) && 
                                <MenuUI>
                                        <MenuExit onClick={() => setMenuDisplay(!menuDisplay)}>X</MenuExit>
                                        <Link to={'/'} onClick={() => setMenuDisplay(!menuDisplay)}>Home</Link>
                                        {/* <Link to={'/'} onClick={() => setMenuDisplay(!menuDisplay)}>Categorias</Link> */}
                                        {/* <Link to={'/'} onClick={() => setMenuDisplay(!menuDisplay)}>Productos</Link> */}
                                        {currentUser ? (
                                            <p onClick={handleLogout} style={{ cursor: "pointer" }}>Cerrar Sesión</p>
                                        ) : (
                                            <Link to="/login" onClick={() => setMenuDisplay(false)}>Iniciar Sesión</Link>
                                        )}
                                        {/*<Link to={'/login'} onClick={() => setMenuDisplay(!menuDisplay)}>Iniciar Sesión</Link>*/}
                                        <Link to={'/signup'} onClick={() => setMenuDisplay(!menuDisplay)}>Registrarse</Link>
                                </MenuUI>
                        }
                </>
        )
}

export default Menu