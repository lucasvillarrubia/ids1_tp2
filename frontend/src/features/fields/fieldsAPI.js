import axios from "axios"
import { BASE_URL } from "../../utils/index.js"

export const getMyFields = (ownerEmail) => async (dispatch) => {
    try {
        console.log("Obteniendo canchas del usuario")
        const res = await axios.get(`${BASE_URL}/fields/owner/${ownerEmail}`)
        dispatch({ type: "fields/setMyFields", payload: res.data })
    } catch (err) {
        console.error("Error al obtener las canchas del usuario:", err)
    }
}

export const updateField = (id, fieldDTO) => async (dispatch) => {
    try {
        const res = await axios.patch(`${BASE_URL}/fields/${id}`, fieldDTO)
        dispatch({ type: "fields/updateOne", payload: res.data })
    } catch (err) {
        console.error("Error al actualizar la cancha:", err)
    }
}

export const deleteField = (id) => async (dispatch) => {
    try {
        await axios.delete(`${BASE_URL}/fields/${id}`)
        dispatch({ type: "fields/removeField", payload: id })
    } catch (err) {
        console.error("Error al eliminar la cancha:", err)
    }
}

