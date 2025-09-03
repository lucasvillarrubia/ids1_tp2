import React, { useState } from 'react'
import { NavbarUI, NavLogo, NavIcon, NavMenu } from './NavbarStyles'
import { HiOutlineMenu, HiOutlineUser } from 'react-icons/hi';
import { useNavigate } from 'react-router-dom';
import Menu from "../menu/Menu"
import Toolbar from './Toolbar';
import UserMenu from '../userInfo/UserMenu';
import { useDispatch, useSelector } from 'react-redux';
import { toggleUserMenuDisplay } from '../../features/users/usersSlice';

const Navbar = () => {
  const [menuDisplay, setMenuDisplay] = useState(false);
  const navigate = useNavigate();
  const { currentUser } = useSelector(state => state.users);
  const dispatch = useDispatch();
  
  return (
    <>
      <NavbarUI>
          <NavLogo src="./logo.png" onClick={() => navigate('/')}/>
          <NavMenu>
            <NavIcon onClick={() => currentUser ? dispatch(toggleUserMenuDisplay()) : navigate('/login')}>
              <HiOutlineUser />
            </NavIcon>
            <NavIcon onClick={() => setMenuDisplay(!menuDisplay)}><HiOutlineMenu /></NavIcon>
          </NavMenu>
      </NavbarUI>
      <UserMenu />
      <Menu menuDisplay={menuDisplay} setMenuDisplay={setMenuDisplay} />
      <Toolbar menuDisplay={menuDisplay} setMenuDisplay={setMenuDisplay} />
    </>
  )
}

export default Navbar