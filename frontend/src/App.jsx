import Navbar from "./components/header/Navbar"
import Toolbar from "./components/header/Toolbar"
import Menu from "./components/menu/Menu"
import Routes from "./routes/Routes"
import Layout from "./components/layout/Layout"
import { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { logout } from "./features/users/usersSlice.js";

function App() {
        const dispatch = useDispatch();

        useEffect(() => {
            const handleUnload = () => {
                dispatch(logout());
                localStorage.removeItem('authToken');
                sessionStorage.removeItem('authToken');
            };

            window.addEventListener('beforeunload', handleUnload);
            return () => window.removeEventListener('beforeunload', handleUnload);
        }, [dispatch]);

        return (
                <Layout>
                        <Navbar />
                        {/* <Menu /> */}
                        <Routes />
                        {/* <Toolbar /> */}
                        {/* <Footer /> */}
                </Layout>
        )
}

export default App
