import React from 'react'
import { ToolbarUI, NavIcon } from './NavbarStyles'
import { HiOutlineHome, HiOutlineMenu, HiOutlineSearch, HiOutlineUser } from 'react-icons/hi';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { toggleUserMenuDisplay } from '../../features/users/usersSlice';


const Toolbar = ({ menuDisplay, setMenuDisplay }) => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const { currentUser } = useSelector(state => state.users);
  return (
        <ToolbarUI>
                <NavIcon onClick={() => currentUser ? dispatch(toggleUserMenuDisplay()) : navigate('/login')}>
                  <HiOutlineUser />
                </NavIcon>
                <NavIcon onClick={() => navigate('/')}><HiOutlineHome /></NavIcon>
                <NavIcon><HiOutlineSearch /></NavIcon>
                <NavIcon onClick={() => setMenuDisplay(!menuDisplay)}><HiOutlineMenu /></NavIcon>
        </ToolbarUI>
  )
}

export default Toolbar


