import React from 'react'
import { Form, Formik, SignupSubmit, ErrorMessageUI } from './SignupFormStyles'
import { ErrorMessage, Field } from 'formik';
import { signupInitialValues } from '../../formik/initialValues'
import { signupValidationSchema } from '../../formik/validationSchema'
import SignupInput from './SignupInput'
import { createUser } from '../../features/users/usersAPI'
import { useNavigate } from 'react-router-dom'
import { AVAILABLE_ZONES_DISPLAY } from "../../utils/constants.js";


const SignupForm = () => {
        const navigate = useNavigate();
        
        return (
                <Formik 
                        initialValues={signupInitialValues}
                        validationSchema={signupValidationSchema} 
                        onSubmit={async (values, actions) => {
                                try {
                                        await createUser(values);
                                        alert("Te registraste correctamente. Ahora podés verificar tu cuenta.");
                                        navigate('/');
                                        actions.resetForm();
                                } catch (error) {
                                        alert(error.message);
                                }
                        }}
                >
                        <Form>
                                <SignupInput name="name" type="text" id="nombre" htmlFor="nombre"
                                             placeholder="Tu nombre">
                                        Nombre
                                </SignupInput>
                                <SignupInput name="lastname" type="text" id="apellido" htmlFor="apellido"
                                             placeholder="Tu apellido">
                                        Apellido
                                </SignupInput>
                                <SignupInput name="email" type="email" id="correo" htmlFor="correo"
                                             placeholder="Tu correo">
                                        Correo electrónico
                                </SignupInput>
                                <SignupInput name="password" type="password" id="contrasenia" htmlFor="contrasenia"
                                             placeholder="Tu nueva contraseña">
                                        Creá una contraseña
                                </SignupInput>
                                <SignupInput name="age" type="number" id="edad" htmlFor="edad" placeholder="Tu edad">
                                        Edad
                                </SignupInput>
                                <div>
                                        <label htmlFor="gender">Género</label>
                                        <Field as="select" name="gender" id="gender">
                                                <option value="">Seleccionar</option>
                                                <option value="male">Masculino</option>
                                                <option value="female">Femenino</option>
                                                <option value="other">Otro</option>
                                        </Field>
                                        <ErrorMessage name="gender">
                                                {msg => <ErrorMessageUI>{msg}</ErrorMessageUI>}
                                        </ErrorMessage>
                                </div>
                                {/*
                                <div>
                                        <label htmlFor="zones">Zonas de Interés</label>
                                        <Field
                                          as="select"
                                          name="zones"
                                          id="zones"
                                          multiple
                                          style={{
                                                  minHeight: '80px',
                                                  width: '25%'
                                          }}
                                        >
                                                {AVAILABLE_ZONES_DISPLAY.map((zone) => (

                                                  <option key={zone.value} value={zone.value}>
                                                          {zone.label}
                                                  </option>
                                                ))}
                                        </Field>
                                        <ErrorMessage name="zones">
                                                {msg => <ErrorMessageUI>{msg}</ErrorMessageUI>}
                                        </ErrorMessage>
                                        <small style={{ display: 'block', marginTop: '5px', color: '#666' }}>
                                                Mantén presionado Ctrl (o Cmd en Mac) para seleccionar múltiples zonas.
                                        </small>
                                </div>
                                */}

                                <SignupSubmit type='submit'>Crear usuario</SignupSubmit>
                        </Form>
                </Formik>
        )
}

export default SignupForm