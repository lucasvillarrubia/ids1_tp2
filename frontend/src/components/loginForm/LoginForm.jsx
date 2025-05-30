import React, { useEffect } from 'react'
import { LoginSubmit, Formik, Form } from './LoginFormStyles'
import { loginValidationSchema } from '../../formik/validationSchema'
import { loginInitialValues } from '../../formik/initialValues'
import LoginInput from './LoginInput'
import { useDispatch, useSelector } from 'react-redux'
import { useNavigate } from 'react-router-dom'
import { loginUser } from '../../features/users/usersAPI'
import { setCurrentUser } from '../../features/users/usersSlice'
import LoadingButton from '../../components/globalComponents/LoadingButton/LoadingButton'


const LoginForm = () => {
        const dispatch = useDispatch();
        const navigate = useNavigate();
        const { currentUser } = useSelector(state => state.users);
        useEffect(() => { if (currentUser) { navigate('/') } }, [navigate, currentUser, dispatch]);

        return (
                <Formik
                        initialValues={loginInitialValues}
                        validationSchema={loginValidationSchema}
                        onSubmit={async (values) => {
                                        const user = await loginUser(values.email, values.password);
                                        if (user) {
                                                dispatch(setCurrentUser({ ...user.user, token: user.token }));
                                                alert("¡Bienvenidx " + user.user.name + "!");
                                        }
                                }
                        }
                >
                        <Form>
                                <LoginInput name='email' type='email' id='correo' htmlFor='correo' placeholder='Tu correo'>
                                        Correo
                                </LoginInput>
                                <LoginInput name='password' type='password' id='contrasenia' htmlFor='contrasenia' placeholder='Tu contraseña'>
                                        Contraseña
                                </LoginInput>
                                {/* <div>
                                        <a href="#">¿Te olvidaste tu contraseña?</a>
                                </div> */}
                                <LoginSubmit type='submit'>Iniciar sesión</LoginSubmit>
                                {/* <LoadingButton type='submit' isLoading={false}> */}
                                        {/* Click me */}
                                {/* </LoadingButton> */}
                        </Form>
                </Formik>
        )
}

export default LoginForm