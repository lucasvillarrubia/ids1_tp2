import React from 'react'
import { Routes as AllRoutes, Route } from 'react-router-dom';
import Login from '../pages/login/Login';
import Signup from '../pages/signup/Signup';
import NotFound from '../pages/notFound/NotFound';
import Home from '../pages/home/Home';
import ProtectedRoute from '../components/globalComponents/ProtectedRoute/ProtectedRoute';


function Routes () {
        return (
                <AllRoutes>
                        <Route path='/' element={<Home />} />
                        <Route path='/login' element={<Login />} />
                        <Route path='/signup' element={<Signup />} />
                        <Route path='*' element={<NotFound />} />
                </AllRoutes>
        )
}

export default Routes