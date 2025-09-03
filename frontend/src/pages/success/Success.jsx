import React from 'react'
import { SuccessBG, SuccessButton } from './SuccessStyles' 
import { useNavigate } from 'react-router-dom'

const Success = () => {
  const navigate = useNavigate();
  return (
    <SuccessBG>
        <p>¡Felicidades! Operación exitosa.</p>
        <SuccessButton onClick={() => { navigate('/') }}>Ir a home</SuccessButton>
    </SuccessBG>
  )
}

export default Success