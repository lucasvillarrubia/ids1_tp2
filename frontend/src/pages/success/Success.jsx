import React from 'react'
import { SuccessBG, SuccessButton } from './SuccessStyles' 
import { useNavigate } from 'react-router-dom'

const Success = () => {
  const navigate = useNavigate();
  return (
    <SuccessBG>
        <p>¡Felicidades! Compra exitosa.</p>
        <SuccessButton onClick={() => { navigate('/') }}>Ir a home</SuccessButton>
        <SuccessButton onClick={() => { navigate('/my-orders') }}>Mirá tus pedidos</SuccessButton>
    </SuccessBG>
  )
}

export default Success