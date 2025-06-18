import React from 'react'
import { Routes as AllRoutes, Route } from 'react-router-dom';
import Login from '../pages/login/Login';
import Signup from '../pages/signup/Signup';
import NotFound from '../pages/notFound/NotFound';
import Home from '../pages/home/Home';
import ProtectedRoute from '../components/globalComponents/ProtectedRoute/ProtectedRoute';
import Create from '../pages/create/Create';
import Success from '../pages/success/Success';
import TeamPage from "../pages/itemPages/teamPage/TeamPage.jsx";


function Routes () {
        return (
                <AllRoutes>
                        <Route path='/' element={<Home />} />
                        <Route path='/login' element={<Login />} />
                        <Route path='/signup' element={<Signup />} />
                        <Route element={<ProtectedRoute redirPath={'/login'} />}>
                            <Route path='/create' element={<Create />} />
                        </Route>
                        <Route element={<ProtectedRoute redirPath={'/login'} />}>
                            <Route path='/congratulations' element={<Success />} />
                        </Route>
                        <Route path="/team/:teamId" element={<TeamPage />} />
                        <Route path='*' element={<NotFound />} />
                </AllRoutes>
        )
}

export default Routes