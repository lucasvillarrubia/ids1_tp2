import axios from 'axios';
import { BASE_URL } from '../../utils/index.js';

export const updateTeam = async (id, teamData) => {
    const response = await axios.patch(`${BASE_URL}/teams/${id}`, teamData);
    if (response) {
        console.log("Se actualizó equipo", res.data);
    }
    return response.data;
};

export const addPlayer = async (id, playerName) => {
    const response = await axios.patch(`${BASE_URL}/teams/${id}/player`, null, {
        params: { playerName },
    });
    if (response) {
        console.log("Se agregó jugador", res.data);
    }
    return response.data;
};

export const removePlayer = async (id, playerName) => {
    const response = await axios.delete(`${BASE_URL}/teams/${id}/player`, {
        params: { playerName },
    });
    if (response) {
        console.log("Se eliminó jugador", res.data);
    }
    return response.data;
};

export const deleteTeam = async (id) => {
    const response = await axios.delete(`${BASE_URL}/teams/${id}`);
    if (response) {
        console.log("Se eliminó equipo", res.data);
    }
    return response.data;
};
