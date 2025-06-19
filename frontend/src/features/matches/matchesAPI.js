import axios from "axios";
import { BASE_URL } from "../../utils";

export const joinMatch = (id) => async (dispatch) => {
    try {
        const res = await axios.post(`${BASE_URL}/matches/join/${id}`);
        dispatch({ type: "matches/updateOne", payload: res.data });
    } catch (err) {
        console.error("joinMatch failed", err);
    }
};

export const leaveMatch = (id) => async (dispatch) => {
    try {
        await axios.post(`${BASE_URL}/matches/leave/${id}`);
    } catch (err) {
        console.error("leaveMatch failed", err);
    }
};

export const startMatch = (id) => async (dispatch) => {
    try {
        const res = await axios.post(`${BASE_URL}/matches/start`, { id });
        if (res) {
            console.log("Match comenzó", res.data);
        }
        dispatch({ type: "matches/updateOne", payload: res.data });
    } catch (err) {
        console.error("startMatch failed", err);
    }
};

export const closeMatch = (id) => async (dispatch) => {
    try {
        await axios.post(`${BASE_URL}/matches/close`, { id });
        dispatch({ type: "matches/updateOne", payload: { id, state: "CLOSED" } });
    } catch (err) {
        console.error("closeMatch failed", err);
    }
};

export const deleteMatch = (id) => async (dispatch) => {
    try {
        await axios.delete(`${BASE_URL}/matches/${id}`);
        dispatch({ type: "matches/removeMatch", payload: id });
    } catch (err) {
        console.error("deleteMatch failed", err);
    }
};

export const updateMatch = (id, matchCreateDTO) => async (dispatch) => {
    try {
        const res = await axios.patch(`${BASE_URL}/matches/${id}`, matchCreateDTO);
        if (res) {
            console.log("Match se editó", res.data);
        }
        dispatch({ type: "matches/updateOne", payload: res.data });
    } catch (err) {
        console.error("updateMatch failed", err);
    }
};
