import React from 'react'
import { useSelector } from 'react-redux'
import { Navigate, Outlet } from 'react-router-dom';
import FlashMessage from "../FlashMessage/FlashMessage.jsx";

const ProtectedRoute = ({ redirPath }) => {
        const { currentUser } = useSelector(state => state.users);
        if (!currentUser) {
                // alert("Tenés que iniciar sesión para realizar esta acción!");
                // return (<Navigate to={redirPath} />);
                return (<FlashMessage redirect={redirPath} message={"Para ver este recurso tenés que iniciar sesión! Aceptá para continuar al Log In"}/>)
        }
        else return (<Outlet />);
}

export default ProtectedRoute