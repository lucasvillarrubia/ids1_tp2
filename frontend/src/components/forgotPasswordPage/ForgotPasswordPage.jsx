import React, { useState } from 'react';
import {Field, ErrorMessage, Form, Formik } from 'formik';
import * as Yup from 'yup';
import { useNavigate } from 'react-router-dom';
import {ForgotPasswordBG, ForgotPasswordButton,ForgotPasswordText,ForgotPasswordTitle, SubmitPasswordButton, PasswordInput} from "./ForgotPasswordPage.js";
import { ErrorMessageUI } from "../loginForm/LoginFormStyles.js";
import { signupInitialValues as values } from "../../formik/initialValues.js";

const InputField = ({label, name, type, placeholder}) => (
  <div style={{ marginBottom: "20px" }}>
    {label && <label htmlFor={name} style={{ display: 'block', marginBottom: '5px', fontWeight: 'bold' }}>{label}</label>}
    <Field name={name} type={type} id={name} placeholder={placeholder}
           style={{ width: 'calc(100% - 22px)', padding: '10px', border: '2px solid black', borderRadius: '15px', boxSizing: 'border-box',fontStyle: "Bebas Neue" }}
    />
    <ErrorMessage name={name} component="div" style={{ color: "black", fontSize: "0.8em", marginTop: "5px", fontStyle: "Bebas Neue", marginBottom: "5px"}} />
  </div>
);

const ForgotPasswordPage = () => {
  const navigate = useNavigate();
  const [statusMessage, setStatusMessage] = useState("Ingresa correo electronico para restablecer clave");
  const [isLoading, setIsLoading] = useState(false);
  const [requestSuccesful, setRequestSuccesful] = useState(false);


  const validationSchema = Yup.object({
    email: Yup.string()
      .email('Formato de correo inválido')
      .required('El correo es obligatorio'),
  });

  const handleRequestReset = async (values, { resetForm }) => {
      setIsLoading(true);
      setStatusMessage('');
      setRequestSuccesful(false);

      try {
        const response = await fetch(`http://localhost:8080/password_reset/request-reset?email=${encodeURIComponent(values.email)}`, {
          method: 'POST',
        });

        if (response.ok) {
          setStatusMessage('¡Solicitud enviada! Revisa tu correo para las instrucciones.');
          setRequestSuccesful(true);
          resetForm();
        } else {
          const errorText = await response.text();
          setStatusMessage(`Error: ${errorText || response.statusText || 'No se pudo enviar la solicitud.'}`);
        }
      } catch (error) {
        console.error('Error al contactar el servidor:', error);
        setStatusMessage('Error de conexión. Intenta de nuevo más tarde.');
      } finally {
        setIsLoading(false);
      }
    };

  return (
    <ForgotPasswordBG>
      <ForgotPasswordTitle>{requestSuccesful ? 'Solicitud Enviada' : 'Recuperar Contraseña'}</ForgotPasswordTitle>
      <ForgotPasswordText>{statusMessage}</ForgotPasswordText>
      {!requestSuccesful && (
          <Formik initialValues={{ email: '' }}
                  validationSchema={validationSchema}
                  onSubmit={handleRequestReset} >
            {({ isSubmitting }) => (
              <Form>
                <InputField
                  // label={"Correo electronico"}
                  name="email"
                  type={"email"}
                  placeholder={"tu_correo@example.com"}
                />
                <SubmitPasswordButton type={"submit"} disabled={isLoading || isSubmitting}>
                  Solicitar Restablecimiento de clave
                </SubmitPasswordButton>
              </Form>
            )}
          </Formik>

      )}
      <ForgotPasswordButton onClick={() => navigate("/login")}>
        Volver al inicio de sesión
      </ForgotPasswordButton>
    </ForgotPasswordBG>
  );
};

export default ForgotPasswordPage;
