import React from 'react'
import { SignupBG, SignupTitle } from './SignupStyles'
import SignupForm from '../../components/signupForm/SignupForm'

const Signup = () => {
  return (
    <SignupBG>
      <SignupTitle>Registro</SignupTitle>
      <SignupForm />
    </SignupBG>
  )
}

export default Signup