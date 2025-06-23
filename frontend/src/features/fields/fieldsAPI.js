import axios from "axios"
import { BASE_URL } from "../../utils/index.js"

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

