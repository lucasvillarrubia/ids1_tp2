import React, { useEffect } from 'react'
import { LoginSubmit, Formik, Form } from './LoginFormStyles'
import { loginValidationSchema } from '../../formik/validationSchema'
import { loginInitialValues } from '../../formik/initialValues'
import LoginInput from './LoginInput'
import { useDispatch, useSelector } from 'react-redux'
import { useNavigate } from 'react-router-dom'
import { loginUser } from '../../features/users/usersAPI'
import { setCurrentUser } from '../../features/users/usersSlice'


const LoginForm = () => {
        const dispatch = useDispatch();
        const navigate = useNavigate();
        const { currentUser } = useSelector(state => state.users);
        useEffect(() => {
                if (currentUser) { navigate('/'); }
        }, [currentUser, navigate]);

        const handleSubmit = async (values, { setSubmitting }) => {
                try {
                        const user = await loginUser(values.email, values.password);
                        if (user) {
                                dispatch(setCurrentUser({ ...user.user, token: user.token }));
                                console.log(currentUser);
                                alert("Entraste!");
                                navigate('/');
                        }
                } catch (error) {
                        alert(error.message);
                } finally {
                        setSubmitting(false);
                }
        };

        return (
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
        )
}

export default LoginForm