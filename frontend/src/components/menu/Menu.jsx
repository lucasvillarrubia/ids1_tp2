import React from 'react'
import { MenuBG, MenuUI, MenuExit } from './MenuStyles'
import { Link } from 'react-router-dom'

const Menu = ({ menuDisplay, setMenuDisplay }) => {
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
                                        <Link to={'/login'} onClick={() => setMenuDisplay(!menuDisplay)}>Iniciar Sesión</Link>
                                        <Link to={'/signup'} onClick={() => setMenuDisplay(!menuDisplay)}>Registrarse</Link>
                                </MenuUI>
                        }
                </>
        )
}

export default Menu