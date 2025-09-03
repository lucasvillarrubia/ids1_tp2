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
    extraReducers: (builder) => {
        builder
            .addCase("matches/updateOne", (state, action) => {
                const updated = action.payload;
                const idx = state.list.findIndex(m => m.id === updated.id);
                if (idx !== -1) {
                    state.list[idx] = updated;
                } else {
                    state.list.push(updated);
                }
            })
            .addCase("matches/removeMatch", (state, action) => {
                state.list = state.list.filter(m => m.id !== action.payload);
            });
    }
});

export const { setList: setMatches, clearList: clearMatches } = matchesActions;
export { fetchMatches };
export default matchesReducer;
