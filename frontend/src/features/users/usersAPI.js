import axios from "axios"
import { BASE_URL } from "../../utils/constants"
import {logout, setCurrentUser,setAuth} from "./usersSlice.js";


/**
 * Crea un nuevo usuario (signup)
 * @param {Object} userData
 * @param {string} userData.name
 * @param {string} userData.lastname
 * @param {string} userData.email
 * @param {string} userData.password
 * @param {number} userData.age
 * @param {string} userData.gender
 * @param {string} userData.zone
 * @returns {Promise<Object>}
 */
export const createUser = async (userData) => {
        try {
                const { data } = await axios.post(`${BASE_URL}/users`, userData);
                return data;
        } catch (error) {
                console.error("createUser error:", error);
                const msg =
                        error?.response?.data?.errors?.[0]?.msg ||
                        error?.response?.data?.message ||
                        error?.message ||
                        "Ocurrió un error al crear el usuario.";
                throw new Error(msg);
        }
};

export const verifyUser = async (email, code) => {
        try {
                const { data } = await axios.patch(`${BASE_URL}/auth/verify`, { email, code });
                return data;
        } catch (error) {
                return alert(error.response.data.msg);
        }
}


export const loginUser = async (email, password) => {


        try {
                const response = await axios.post(`${BASE_URL}/sessions`, { email, password });
                const token = response.data.accessToken;

                axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
                const userResponse = await axios.get(`${BASE_URL}/sessions/me`);
                const name = userResponse.data.name;
                return { token, name };
        } catch (error) {
                console.error("Login error:", error);
                const msg = error?.response?.data?.msg || "Unexpected login error";
                throw new Error(msg);
        }
};


export const getUserProfile = async (token) => {
        try {

                const userProfileResponse = await axios.get(`${BASE_URL}/sessions/profile`, {
                        headers: {
                                'Authorization': `Bearer ${token}`
                        }
                });

                return userProfileResponse
        } catch (error) {
                console.error("getUserProfile error:", error);

                
                const msg =
                  error?.response?.data?.message ||
                  error?.message ||              
                  "Ocurrió un error desconocido al cargar el perfil del usuario.";

                throw new Error(msg);
        }
};


export const logoutUser = (dispatch) => {
        delete axios.defaults.headers.common["Authorization"];
        dispatch(logout());
};
