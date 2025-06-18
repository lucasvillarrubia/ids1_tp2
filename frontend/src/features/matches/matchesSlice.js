import { makeEntityAPI } from "../templateAPI";
import { makeEntitySlice } from "../templateSlice";

const matchesAPI = makeEntityAPI("matches");

const {
    reducer: matchesReducer,
    actions: matchesActions,
    fetchThunk: fetchMatches,
} = makeEntitySlice({
    entityName: "matches",
    fetchAll: matchesAPI.getAll,
});

export const { setList: setMatches, clearList: clearMatches } = matchesActions;
export { fetchMatches };
export default matchesReducer;
