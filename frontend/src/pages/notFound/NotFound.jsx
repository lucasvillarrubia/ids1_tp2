import React from 'react'
import { NotFoundBG, NotFoundButton } from './NotFoundStyles' 
import { useNavigate } from 'react-router-dom'

const NotFound = () => {
  const navigate = useNavigate();
  return (
    <NotFoundBG>
        <p>404: Not Found</p>
        <NotFoundButton onClick={() => navigate('/')}>Ir a algún lado</NotFoundButton>
    </NotFoundBG>
  )
}

export default NotFound