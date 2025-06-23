import axios from 'axios';
import { BASE_URL } from '../../utils/index.js';

export const updateTeam = async (id, teamData) => {
    const response = await axios.patch(`${BASE_URL}/teams/${id}`, teamData);
    return response.data;
};

export const addPlayer = async (id, playerName) => {
    const response = await axios.patch(`${BASE_URL}/teams/${id}/player`, null, {
        params: { playerName },
    });
    return response.data;
};

export const removePlayer = async (id, playerName) => {
    const response = await axios.delete(`${BASE_URL}/teams/${id}/player`, {
        params: { playerName },
    });
    return response.data;
};

export const deleteTeam = async (id) => {
    const response = await axios.delete(`${BASE_URL}/teams/${id}`);
    return response.data;
};
