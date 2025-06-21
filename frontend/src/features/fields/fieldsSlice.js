import { makeEntityAPI } from "../templateAPI"
import { makeEntitySlice } from "../templateSlice"

const fieldsAPI = makeEntityAPI("fields")

const {
    reducer: fieldsReducer,
    actions: fieldsActions,
    fetchThunk: fetchFields,
} = makeEntitySlice({
    entityName: "fields",
    fetchAll: fieldsAPI.getAll,

    extraReducers: (builder) => {
        builder
            .addCase("fields/updateOne", (state, action) => {
                const updated = action.payload
                const idx = state.list.findIndex(f => f.id === updated.id)
                if (idx !== -1) {
                    state.list[idx] = updated
                } else {
                    state.list.push(updated)
                }
            })
            .addCase("fields/removeField", (state, action) => {
                state.list = state.list.filter(f => f.id !== action.payload)
            })
    }
})

export const { setList: setFields, clearList: clearFields } = fieldsActions
export { fetchFields }
export default fieldsReducer
