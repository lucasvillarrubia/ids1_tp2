import axios from "axios";
import {BASE_URL} from "../utils/index.js";

export const makeEntityAPI = (entityName) => {
    let entityUrl = `${BASE_URL}/${entityName}`;
    return {
        getAll: async () => {
            const res = await axios.get(entityUrl);
            return res.data;
        },
        getById: async (id) => {
            const res = await axios.get(`${entityUrl}/${id}`);
            return res.data;
        },
        create: async (data) => {
            const res = await axios.post(entityUrl, data);
            return res.data;
        },
        update: async (id, data) => {
            const res = await axios.put(`${entityUrl}/${id}`, data);
            return res.data;
        },
        remove: async (id) => {
            const res = await axios.delete(`${entityUrl}/${id}`);
            return res.data;
        },
    };
};
