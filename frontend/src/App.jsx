import Navbar from "./components/header/Navbar"
import Toolbar from "./components/header/Toolbar"
import Menu from "./components/menu/Menu"
import Routes from "./routes/Routes"
import axios from "axios";
import Layout from "./components/layout/Layout"
import { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { logout } from "./features/users/usersSlice.js";

function App() {
        const dispatch = useDispatch();

        useEffect(() => {
            const token = localStorage.getItem('token');
            if (token) {
                axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
            }
        }, []);

        return (
                <Layout>
                        <Navbar />
                        {}
                        <Routes />
                        {}
                        {}
                </Layout>
        )
}

export default App
