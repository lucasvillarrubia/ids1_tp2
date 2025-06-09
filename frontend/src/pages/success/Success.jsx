import React from 'react'
import { SuccessBG, SuccessButton } from './SuccessStyles' 
import { useNavigate } from 'react-router-dom'

const Success = () => {
  const navigate = useNavigate();
  return (
    <SuccessBG>
        <p>¡Felicidades! Creación exitosa.</p>
        <SuccessButton onClick={() => { navigate('/') }}>Ir a home</SuccessButton>
        {/*<SuccessButton onClick={() => { navigate('/my-profile') }}>Mirá tus cosas</SuccessButton>*/}
    </SuccessBG>
  )
}

export default Success