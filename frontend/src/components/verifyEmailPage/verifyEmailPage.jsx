import React, { useEffect, useState } from "react";
import { useSearchParams, useNavigate } from "react-router-dom";
import { VerifyEmailBG, VerifyEmailButton, VerifyEmailText, VerifyEmailTitle } from "./verifyEmailPage.js";
import { ErrorMessage, Field } from "formik";


const VerifyEmailPage = () => {
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();
  const [status, setStatus] = useState('Verificando...');

  useEffect(() => {
    const email = searchParams.get("email");
    const token = searchParams.get("token");

    if (!token) {
      setStatus("Token no encontrado. Enlace inválido.");
      setTimeout(() => navigate('/login'), 3000); // Redirigir si no hay token
      return;
    }

    const verify = async () => {
      try {
        const response = await fetch(`http://localhost:8080/mail/verify-email/${token}`, {
          method: "GET",
        });

        if (response.ok) {
          setStatus("¡Cuenta verificada con éxito!");
          setTimeout(() => {
            navigate("/login");
          }, 4000);
        } else {
          const errorData = await response.json().catch(() => ({ message: 'Error desconocido' })); // Intenta parsear el error del backend
          setStatus(`Error al verificar cuenta: ${errorData.message || response.statusText || 'Inténtalo de nuevo.'}`);
        }
      } catch (error) {
        console.error("Error de red o servidor:", error);
        setStatus("Error al contactar el servidor. Por favor, inténtalo de nuevo más tarde.");
      }
    };
    verify();
  }, [navigate, searchParams]); // Las dependencias están bien

  return (
    <VerifyEmailBG>
      {/* Muestra el mensaje de estado dinámicamente */}
      <VerifyEmailTitle>{status}</VerifyEmailTitle>
      {/* Solo muestra el texto de redirección si la verificación fue exitosa */}
      {status.includes("éxito") && (
        <VerifyEmailText>Serás redirigido a la página de inicio de sesión en unos segundos...</VerifyEmailText>
      )}
      <VerifyEmailButton onClick={() => navigate("/")}>
        Ir al inicio ahora
      </VerifyEmailButton>
    </VerifyEmailBG>
  );
};

export default VerifyEmailPage;

const InputField = ({ label, name, type, placeholder }) => (
  <div>
    <label htmlFor={name}>{label}</label>
    <Field name={name} type={type} id={name} placeholder={placeholder} />
    <ErrorMessage name={name} component="div" style={{ color: 'red', fontSize: '0.8em' }} />
  </div>
);

const SubmitButton = ({ children, isLoading }) => (
  <button type="submit" disabled={isLoading} style={{ padding: '10px 20px', cursor: isLoading ? 'not-allowed' : 'pointer' }}>
    {isLoading ? 'Enviando...' : children}
  </button>
);
