import { makeEntityAPI } from "../templateAPI";
import { makeEntitySlice } from "../templateSlice";

const tournamentsAPI = makeEntityAPI("tournaments");

const {
    reducer: tournamentsReducer,
    actions: tournamentsActions,
    fetchThunk: fetchTournaments,
} = makeEntitySlice({
    entityName: "tournaments",
    fetchAll: tournamentsAPI.getAll,
});

export const { setList: setTournaments, clearList: clearTournaments } = tournamentsActions;
export { fetchTournaments };
export default tournamentsReducer;
