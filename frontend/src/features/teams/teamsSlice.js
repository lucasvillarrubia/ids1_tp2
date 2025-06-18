import { makeEntityAPI } from "../templateAPI";
import { makeEntitySlice } from "../templateSlice";

const teamsAPI = makeEntityAPI("teams");

const {
    reducer: teamsReducer,
    actions: teamsActions,
    fetchThunk: fetchTeams,
} = makeEntitySlice({
    entityName: "teams",
    fetchAll: teamsAPI.getAll,
});

export const { setList: setTeams, clearList: clearTeams } = teamsActions;
export { fetchTeams };
export default teamsReducer;
