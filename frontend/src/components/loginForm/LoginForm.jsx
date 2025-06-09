import React, { useEffect, useState } from 'react'
import { LoginSubmit, Formik, Form } from './LoginFormStyles'
import { loginValidationSchema } from '../../formik/validationSchema'
import { loginInitialValues } from '../../formik/initialValues'
import LoginInput from './LoginInput'
import { useDispatch, useSelector } from 'react-redux'
import { useNavigate } from 'react-router-dom'
import { loginUser } from '../../features/users/usersAPI'
import { setAuth, setCurrentUser } from '../../features/users/usersSlice'
import FlashMessage from "../globalComponents/FlashMessage/FlashMessage.jsx";


const LoginForm = () => {
        const dispatch = useDispatch();
        const navigate = useNavigate();
        const [msg, setMsg] = useState(null);
        const [type, setType] = useState('info');
        const [redirectAfterClose, setRedirectAfterClose] = useState(false);
        const { currentUser } = useSelector(state => state.users);

        // useEffect(() => {
        //         if (currentUser) { navigate('/');
        //         }
        // }, [currentUser, navigate]);

        const showLoginMessage = (type, msg, shouldRedirect = false) => {
                console.log('Showing login message:', msg);
                setTimeout(() => {
                        setType(type);
                        setMsg(msg);
                        setRedirectAfterClose(shouldRedirect);
                }, 0);
        };

        const handleSubmit = async (values, { setSubmitting }) => {
                try {
                        const { token, name } = await loginUser(values.email, values.password);
                        if (token) {
                                showLoginMessage('success', 'Inicio de sesión exitoso', true);
                                setTimeout(() => {
                                        dispatch(setCurrentUser({ name, token }));
                                        dispatch(setAuth({ user: { name, email: values.email }, token }));
                                        console.log(currentUser);
                                        // alert("Entraste!");
                                        navigate('/');
                                }, 5000);
                        }
                } catch (error) {
                        showLoginMessage('error', 'Error al iniciar sesión');
                        // alert(error.message);
                } finally {
                        setSubmitting(false);
                }
        };

        const handleCloseMessage = () => {
                setMsg(null);
                // if (redirectAfterClose) {
                        // navigate('/');
                // }
        };

        let message_duration = redirectAfterClose ? 5000 : 0;

        return (
            <>
                    {msg && (
                        <FlashMessage
                            message={msg}
                            type={type}
                            duration={message_duration}
                            onClose={handleCloseMessage}
                            fromLogin={true}
                        />
                    )}
                    {/*{msg && (*/}
                    {/*    <div style={{ background: "yellow", padding: "10px" }}>*/}
                    {/*            {msg}*/}
                    {/*    </div>*/}
                    {/*)}*/}
                    <Formik
                        initialValues={loginInitialValues}
                        validationSchema={loginValidationSchema}
                        onSubmit={handleSubmit}
                    >
                        {({ isSubmitting }) => (
                            <Form>
                                <LoginInput name="email" type="email" id="correo" htmlFor="correo" placeholder="Tu correo">
                                    Correo
                                </LoginInput>
                                <LoginInput name="password" type="password" id="contrasenia" htmlFor="contrasenia" placeholder="Tu contraseña">
                                    Contraseña
                                </LoginInput>
                                <LoginSubmit type="submit" disabled={isSubmitting}>
                                    {isSubmitting ? 'Cargando...' : 'Iniciar sesión'}
                                </LoginSubmit>
                            </Form>
                        )}
                    </Formik>
            </>
        )
}

export default LoginForm