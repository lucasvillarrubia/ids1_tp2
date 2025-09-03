import axios from 'axios';
import { BASE_URL } from '../../utils/index.js';

export const getMyTeams = (email) => async (dispatch) => {
    try {
        const response = await axios.get(`${BASE_URL}/teams`);
        console.log("Se obtuvieron equipos, ahora se filtran", response.data);
        dispatch({ type: "teams/setList", payload: response.data.filter(team => team.captain === email) });
    } catch (err) {
        console.error("getMyTeams failed", err);
    }
}

export const updateTeam = async (id, teamData) => {
    const response = await axios.patch(`${BASE_URL}/teams/${id}`, teamData);
    return response.data;
};

export const addPlayer = async (id, playerName) => {
    const response = await axios.patch(`${BASE_URL}/teams/${id}/player`, playerName);
    if (response) {
        console.log("Se agregó jugador", res.data);
    }
    return response.data;
};

export const removePlayer = async (id, playerName) => {
    const response = await axios.delete(`${BASE_URL}/teams/${id}/player`, playerName);
    if (response) {
        console.log("Se eliminó jugador", res.data);
    }
    return response.data;
};

export const deleteTeam = async (id) => {
    const response = await axios.delete(`${BASE_URL}/teams/${id}`);
    return response.data;
};
