import axios from "axios"
import { BASE_URL } from "../../utils/constants"
import { Item } from '../../staticData/Items.jsx'
import { setItems } from './itemsSlice.js'

export const loadItemsByGenre = (genre) => async (dispatch) => {
        if (!genre) {
                dispatch(setItems([]));
                return;
        }
        try {
                console.log(genre);
                const res = await axios.get(`${BASE_URL}/${genre}`);
                const data = res.data;
                const allKeys = new Set();
                data.forEach(item => {
                        Object.keys(item).forEach(key => allKeys.add(key));
                });
                let index = 0;
                const items = data.map(i => {
                        index++;
                        return new Item(i, index);
                });
                dispatch(setItems(items));
        } catch (err) {
                console.error("Falló el fetch de ítems:", err);
        }
};

/**
 * Crea un nuevo ítem (e.g., equipo, partido, cancha etc.)
 * @param {string} genre - El género del ítem a crear (e.g., 'teams', 'matches', 'fields').
 * @param {Object} itemData - El objeto que contiene los datos del ítem a crear.
 */
export const createItem = async (genre, itemData) => {
        try {
                const res = await axios.post(`${BASE_URL}/${genre}`, itemData);
                return res.data;
        } catch (error) {
                const message =
                    error.response?.data?.message || 'Error al crear el item';
                throw new Error(message);
        }
};

