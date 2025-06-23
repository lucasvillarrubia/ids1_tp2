import React from 'react'
import { UserMenuBG, UserMenuUI } from './UserStyles'
import { useDispatch, useSelector } from 'react-redux'
import {logout, setCurrentUser, toggleUserMenuDisplay} from '../../features/users/usersSlice';
import { useNavigate } from 'react-router-dom';
import { logoutUser } from "../../features/users/usersAPI.js";

const UserMenu = () => {
        const dispatch = useDispatch();
        const navigate = useNavigate();
        const { currentUser, userMenuOnDisplay } = useSelector(state => state.users);
        return (
                <>
                        {(userMenuOnDisplay) && 
                                <UserMenuBG onClick={() => dispatch(toggleUserMenuDisplay())}></UserMenuBG>
                        }
                        {(userMenuOnDisplay) &&
                          <UserMenuUI>
                                  <h6>{`¡Hola ${currentUser?.name || "Invitado"}!`}</h6>
                                  <p onClick={() => {
                                          dispatch(toggleUserMenuDisplay());
                                          navigate("/me");
                                  }}>
                                          Mí Perfíl
                                  </p>
                                  {/*<p onClick={() => {*/}
                                  {/*        dispatch(toggleUserMenuDisplay());*/}
                                  {/*        // navigate('my-orders');*/}
                                  {/*        alert("Todavía no lo terminé de implementar!");*/}
                                  {/*}}*/}
                                  {/*>*/}
                                  {/*        Tus partidos*/}
                                  {/*</p>*/}
                                  {/*<p onClick={() => {*/}
                                  {/*        dispatch(toggleUserMenuDisplay());*/}
                                  {/*        // navigate('my-orders');*/}
                                  {/*        alert("Todavía no lo terminé de implementar!");*/}
                                  {/*}}*/}
                                  {/*>*/}
                                  {/*        Tus equipos*/}
                                  {/*</p>*/}
                                  {/*<p onClick={() => {*/}
                                  {/*        dispatch(toggleUserMenuDisplay());*/}
                                  {/*        // navigate('my-orders');*/}
                                  {/*        alert("Todavía no lo terminé de implementar!");*/}
                                  {/*}}*/}
                                  {/*>*/}
                                  {/*        Tus torneos*/}
                                  {/*</p>*/}
                                  {/*<p onClick={() => {*/}
                                  {/*        dispatch(toggleUserMenuDisplay());*/}
                                  {/*        // navigate('my-orders');*/}
                                  {/*        alert("Todavía no lo terminé de implementar!");*/}
                                  {/*}}*/}
                                  {/*>*/}
                                  {/*        Tus reviews*/}
                                  {/*</p>*/}
                                  <p onClick={() => {
                                          logoutUser(dispatch);
                                          dispatch(toggleUserMenuDisplay());
                                          navigate("/");
                                  }}
                                  >
                                          Cerrar sesión
                                  </p>
                          </UserMenuUI>
                        }
                </>
        )
}

export default UserMenu;