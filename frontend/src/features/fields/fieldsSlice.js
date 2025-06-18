import { makeEntityAPI } from "../templateAPI";
import { makeEntitySlice } from "../templateSlice";

const fieldsAPI = makeEntityAPI("fields");

const {
    reducer: fieldsReducer,
    actions: fieldsActions,
    fetchThunk: fetchFields,
} = makeEntitySlice({
    entityName: "fields",
    fetchAll: fieldsAPI.getAll,
});

export const { setList: setFields, clearList: clearFields } = fieldsActions;
export { fetchFields };
export default fieldsReducer;
