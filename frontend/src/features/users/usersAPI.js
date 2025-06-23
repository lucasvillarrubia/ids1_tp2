import axios from "axios"
import { BASE_URL } from "../../utils/constants"
import {logout} from "./usersSlice.js";
import {selectCategory} from "../categories/categoriesSlice.js";
import {clearItems} from "../items/itemsSlice.js";


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
                console.log("Status code:", error.response?.status);
                console.log("Error data:", error.response?.data);
                throw new Error(msg);
        }
};


export const loginUser = async (email, password) => {


        try {
                const response = await axios.post(`${BASE_URL}/sessions`, { email, password });
                const token = response.data.accessToken;
                localStorage.setItem('token', token);
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
                                'Authorization': `Bearer ${token}` // <- Aquí te aseguras que el token se envía
                        }
                });

                return userProfileResponse
        } catch (error) {
                console.error("getUserProfile error:", error);

                // Extract a meaningful error message from the response
                const msg =
                  error?.response?.data?.message || // For messages from your Spring backend
                  error?.message ||                  // For network errors or generic JS errors
                  "Ocurrió un error desconocido al cargar el perfil del usuario.";

                throw new Error(msg); // Re-throw a standardized Error for the calling component to catch
        }
};


export const logoutUser = (dispatch) => {
        console.log("Se cerró sesión");
        delete axios.defaults.headers.common["Authorization"];
        localStorage.removeItem('token');
        dispatch(selectCategory(null));
        dispatch(clearItems());
        dispatch(logout());
};
