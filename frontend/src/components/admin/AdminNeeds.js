import axios from 'axios';
import {BASE_URL} from "../../utils/constants";

/**
 * Para agregar un endpoint para probar:
 * - en label se ponés el nombre que querés que aparezca en el select
 * - en value ponés la URL del endpoint, que va a tener la forma `/nombreDelEndpoint`, la url base se concatena más abajo
 */
export const endpoints = [
    { label: 'Fields', value: '/fields' },
    { label: 'Matches', value: '/matches' },
    { label: 'Teams', value: '/teams' },
    { label: 'Users', value: '/users' },
    { label: 'Tournaments', value: '/tournaments' },
    { label: 'Sessions', value: '/sessions' }
];

export const methods = ['GET', 'POST', 'PATCH', 'DELETE'];

export const sendRequest = async ({ method, endpoint, jsonInput }) => {
    try {
        const hasBody = method !== 'GET' && method !== 'DELETE';
        const data = hasBody ? JSON.parse(jsonInput) : undefined;

        const response = await axios({
            method,
            url: `${BASE_URL}` + endpoint,
            headers: {
                'Content-Type': 'application/json',
            },
            data,
        });
        console.log('Response:', response.data);
        return JSON.stringify(response.data, null, 2);
    } catch (error) {
        if (error.response) {
            console.error('Error response:', error.response.data);
            return JSON.stringify(error.response.data, null, 2);
        } else if (error.request) {
            return '❌ No hubo respuesta!!';
        } else {
            return `❌ Error: ${error.message}`;
        }
    }
};
