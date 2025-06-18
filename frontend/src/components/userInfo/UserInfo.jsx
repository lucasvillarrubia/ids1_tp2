import React from 'react'
import { useSelector } from 'react-redux'
import { UserInfoUI } from './UserStyles.js'

const UserInfo = () => {
    const { currentUser } = useSelector(state => state.users);

  return (
    <UserInfoUI>
        <p>Usuario: {currentUser.name}</p>
        {/*<p>Correo: {currentUser.user.email}</p>*/}
        {/*<p>Tipo de usuario: </p>*/}
    </UserInfoUI>
  )
}

export default UserInfo