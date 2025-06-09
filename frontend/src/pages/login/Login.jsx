import { useSelector } from 'react-redux'
import React from 'react'
import { LoginBG, LoginText, LoginTitle, LoginButton } from './LoginStyles'
import LoginForm from '../../components/loginForm/LoginForm'
import { useNavigate } from 'react-router-dom'

const Login = () => {
        const navigate = useNavigate();
        const { currentUser } = useSelector(state => state.users);

        if (currentUser) {
                navigate('/');
                return null;
        }

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