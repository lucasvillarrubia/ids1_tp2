import { useSelector } from 'react-redux'
import React, { useEffect, useState } from "react";
import { LoginBG, LoginText, LoginTitle, LoginButton } from './LoginStyles'
import LoginForm from '../../components/loginForm/LoginForm'
import { useNavigate } from 'react-router-dom'


const Login = () => {
        const navigate = useNavigate();
        const { currentUser } = useSelector(state => state.users);
        const [isAuthenticated, setIsAuthenticated] = useState(false);


        useEffect(() => {
          if (isAuthenticated) {
            navigate('/');
            setIsAuthenticated(true);
          }
        }, [isAuthenticated, navigate]);



  return (
                <LoginBG>
                        <LoginTitle>INICIÁ SESIÓN!</LoginTitle>
                        <LoginForm />
                        <LoginText>¿No tenés una cuenta todavía?</LoginText>
                        <LoginButton onClick={() => navigate('/signup')}>Registrate</LoginButton>
                </LoginBG>
        )
}

export default Login
//if (currentUser) {
//       navigate('/');
//      return null;
// }