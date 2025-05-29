import React from 'react'
import { UserInfoUI } from './UserStyles'

const UserInfo = () => {
  return (
    <UserInfoUI>
        <p>Usuario: Ezequiel Martínez</p>
        <p>Correo: villar.lucase@gmail.com</p>
        <a href="#">No soy yo, cerrar sesión.</a>
    </UserInfoUI>
  )
}

export default UserInfo