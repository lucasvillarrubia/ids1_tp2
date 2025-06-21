import React from 'react'
import { useSelector } from 'react-redux'
import { Navigate, Outlet } from 'react-router-dom';

const ProtectedRoute = ({ redirPath }) => {
        const { currentUser } = useSelector(state => state.users);
        if (!currentUser) {
                alert("Tenés que iniciar sesión para realizar esta acción!");
                return (<Navigate to={redirPath} />);
        }
        else return (<Outlet />);
}

export default ProtectedRoute