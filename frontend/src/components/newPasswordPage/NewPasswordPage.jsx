import React, { useEffect, useState } from "react";
import {Field, ErrorMessage, Form, Formik } from 'formik';
import * as Yup from 'yup';
import { useNavigate, useSearchParams } from "react-router-dom";
import {NewPasswordBG,NewPasswordText,NewPasswordTitle, NewPasswordInput, NewPasswordButton,NewSubmitPasswordButton } from "./NewPasswordPage.js";


const InputField = ({label, name, type, placeholder}) => (
  <div style={{ marginBottom: "20px" }}>
    {label && <label htmlFor={name} style={{ display: 'block', marginBottom: '5px', fontWeight: 'bold' }}>{label}</label>}
    <Field name={name} type={type} id={name} placeholder={placeholder}
           style={{ width: 'calc(100% - 22px)', padding: '10px', border: '2px solid black',
             borderRadius: '15px', boxSizing: 'border-box',fontStyle: "Bebas Neue", background:"#fff" }}
    />
    <ErrorMessage name={name} component="div" style={{ color: "black", fontSize: "0.8em", marginTop: "5px", fontStyle: "Bebas Neue", marginBottom: "5px"}} />
  </div>
);
const SubmitButton = ({ children, isLoading }) => (
  <button type="submit" disabled={isLoading}
          style={{ width: 'calc(100% - 22px)', padding: '10px', border: '2px solid black', borderRadius: '15px',
            boxSizing: 'border-box',fontStyle: "Bebas Neue" ,  fontSize: "15px",background:"#ffc907ff",
            cursor: isLoading ? 'not-allowed' : 'pointer' }}>
    {isLoading ? 'Enviando...' : children}
  </button>
);
const NewPasswordPage = () => {
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();
  const [token, setToken] = useState(null);
  const [statusMessage, setStatusMessage] = useState("Verificando token");
  const [isLoading, setIsLoading] = useState(true);
  const [isTokenverified, setIsTokenverified] = useState(false);
  const [passwordResetSuccesful, setPasswordResetSuccesful] = useState(false);

  useEffect(() => {
    const token = searchParams.get("token");
    if (!token) {
      setStatusMessage("Token no valido");
      setIsLoading(false);
      setTimeout(() => navigate("/login"), 3000);
      return;
    }
    setToken(token);
  }, [searchParams, navigate]);

  const validationSchema = Yup.object().shape({
    password : Yup.string()
      .min(8, "La clave debe tener al menos 8 caracteres")
      .required("El campo es obligatorio"),
    confirmPassword: Yup.string()
      .oneOf([Yup.ref('password'), null]).required("confirma clave"),
  });
  const handleSetNewPassword = async (values, { resetForm }) => {
    setIsLoading(true);
    setStatusMessage('');
    setPasswordResetSuccesful(false);

    try {
      const response = await fetch
      (`http://localhost:8080/password_reset/new-password?token=${encodeURIComponent(token)}&newPassword=${encodeURIComponent(values.password)}`, {
        method: 'POST',
      });

      if (response.ok) {
        setPasswordResetSuccesful(true);
        setStatusMessage('¡Contraseña restablecida con éxito! Serás redirigido al inicio de sesión.');
        resetForm();
      } else {
        const errorData = await response.json().catch(() => ({ message: 'Error desconocido' }));
        setStatusMessage(`Error al restablecer la contraseña: ${errorData.message || response.statusText || 'Inténtalo de nuevo.'}`);
      }
    } catch (error) {
      console.error('Error al contactar el servidor:', error);
      setStatusMessage('Error de conexión. Intenta de nuevo más tarde.');
    } finally {
      setIsLoading(false);
    }
  };


  return (
    <NewPasswordBG>
      <NewPasswordTitle>
        {passwordResetSuccesful ? '¡Éxito!' : 'Restablecer Contraseña'}
      </NewPasswordTitle>
      <NewPasswordText>{statusMessage}</NewPasswordText>

      {/* Solo muestra el formulario si hay un token en la URL y aún no se ha restablecido la contraseña */}
      {token && !passwordResetSuccesful && (
        <div style={{ padding: '20px', maxWidth: '400px', borderRadius: '8px' }}>
          <Formik
            initialValues={{ password: '', confirmPassword: '' }}
            validationSchema={validationSchema}
            onSubmit={handleSetNewPassword}
          >
            {({ isSubmitting, errors, touched }) => (
              <Form>
                <InputField
                  label="Nueva Contraseña"
                  name="password"
                  type="password"
                  placeholder="********"
                />
                <InputField
                  label="Confirmar Contraseña"
                  name="confirmPassword"
                  type="password"
                  placeholder="********"
                />
                <SubmitButton type="submit" disabled={isLoading || isSubmitting}>
                  Establecer Nueva Clave
                </SubmitButton>
              </Form>
            )}
          </Formik>
        </div>
      )}

      {/* Mensaje de éxito después del restablecimiento */}
      {passwordResetSuccesful&& (
        <NewPasswordText>Serás redirigido a la página de inicio de sesión en unos segundos...</NewPasswordText>
      )}

      {/* Mensaje si no hay token en la URL */}
      {!token && !isLoading && (
        <NewPasswordText>
          Para restablecer tu contraseña, por favor usa el enlace que recibiste en tu correo electrónico.
        </NewPasswordText>
      )}

      {/* Botón para volver al login */}
      <NewPasswordButton onClick={() => navigate("/login")}>
        Volver al inicio de sesión
      </NewPasswordButton>
    </NewPasswordBG>
  );
};
export default NewPasswordPage;