import axios from "axios";

const BASE_URL = "/api/matches";

export const joinMatch = (id) => async (dispatch) => {
    try {
        const res = await axios.post(`${BASE_URL}/join/${id}`);
        dispatch({ type: "matches/updateOne", payload: res.data });
    } catch (err) {
        console.error("joinMatch failed", err);
    }
};

export const leaveMatch = (id) => async (dispatch) => {
    try {
        await axios.post(`${BASE_URL}/leave/${id}`);
    } catch (err) {
        console.error("leaveMatch failed", err);
    }
};

export const startMatch = (id) => async (dispatch) => {
    try {
        const res = await axios.post(`${BASE_URL}/start?id=${id}`);
        dispatch({ type: "matches/updateOne", payload: res.data });
    } catch (err) {
        console.error("startMatch failed", err);
    }
};

export const closeMatch = (id) => async (dispatch) => {
    try {
        await axios.post(`${BASE_URL}/close?id=${id}`);
        dispatch({ type: "matches/updateOne", payload: { id, state: "CLOSED" } });
    } catch (err) {
        console.error("closeMatch failed", err);
    }
};

export const deleteMatch = (id) => async (dispatch) => {
    try {
        await axios.delete(`${BASE_URL}/${id}`);
        dispatch({ type: "matches/removeMatch", payload: id });
    } catch (err) {
        console.error("deleteMatch failed", err);
    }
};

export const updateMatch = (id, matchCreateDTO) => async (dispatch) => {
    try {
        const res = await axios.patch(`${BASE_URL}/${id}`, matchCreateDTO);
        dispatch({ type: "matches/updateOne", payload: res.data });
    } catch (err) {
        console.error("updateMatch failed", err);
    }
};
