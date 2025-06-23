import React from 'react'
import { Routes as AllRoutes, Route } from 'react-router-dom';
import Login from '../pages/login/Login';
import Signup from '../pages/signup/Signup';
import NotFound from '../pages/notFound/NotFound';
import Home from '../pages/home/Home';
import ProtectedRoute from '../components/globalComponents/ProtectedRoute/ProtectedRoute';
import Create from '../pages/create/Create';
import Success from '../pages/success/Success';
import VerifyEmailPage from "../components/verifyEmailPage/verifyEmailPage.jsx";
import ForgotPasswordPage from "../components/forgotPasswordPage/ForgotPasswordPage.jsx";
import NewPasswordPage from "../components/newPasswordPage/NewPasswordPage.jsx";
import MyProfile from "../pages/myProfile/MyProfile.jsx";
import TeamPage from "../pages/itemPages/teamPage/TeamPage.jsx";
import MatchPage from "../pages/itemPages/matchPage/MatchPage.jsx";
import FieldPage from "../pages/itemPages/fieldPage/FieldPage.jsx";



function Routes () {
        return (
                <AllRoutes>
                        <Route path='/' element={<Home />} />
                        <Route path='/login' element={<Login />} />
                        <Route path='/signup' element={<Signup />} />
                        <Route path="/verify-email" element={<VerifyEmailPage/>} />
                        <Route path="/password_reset/" element={<ForgotPasswordPage/>} />
                        <Route path="password_reset/new_password" element={<NewPasswordPage/>} />
                        {/*<Route path = "/sessions/profile" element={<UserProfilePage/>} />*/}

                        <Route element={<ProtectedRoute redirPath={'/login'} />}>
                            <Route path='/create' element={<Create />} />
                        </Route>
                        <Route element={<ProtectedRoute redirPath={'/login'} />}>
                            <Route path='/me' element={<MyProfile />} />
                        </Route>
                        <Route element={<ProtectedRoute redirPath={'/login'} />}>
                            <Route path='/congratulations' element={<Success />} />
                        </Route>
                        <Route element={<ProtectedRoute redirPath={'/login'} />}>
                            <Route path="/teams/:teamId" element={<TeamPage />} />
                        </Route>
                        <Route element={<ProtectedRoute redirPath={'/login'} />}>
                            <Route path="/matches/:matchId" element={<MatchPage />} />
                        </Route>
                        <Route element={<ProtectedRoute redirPath={'/login'} />}>
                            <Route path="/fields/:fieldId" element={<FieldPage />} />
                        </Route>
                        <Route path='*' element={<NotFound />} />
                </AllRoutes>
        )
}

export default Routes