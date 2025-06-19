import { createAsyncThunk } from '@reduxjs/toolkit';
import { makeEntityAPI } from "../templateAPI";
import { makeEntitySlice } from "../templateSlice";
import {addPlayer, deleteTeam, removePlayer, updateTeam} from "./teamsAPI.js";

const teamsAPI = makeEntityAPI("teams");

export const updateTeamThunk = createAsyncThunk(
    'teams/updateTeam',
    async ({ id, teamData }) => {
        return await updateTeam(id, teamData);
    }
);

export const deleteTeamThunk = createAsyncThunk(
    'teams/deleteTeam',
    async (id) => {
        await deleteTeam(id);
        return id;
    }
);

export const addPlayerThunk = createAsyncThunk(
    'teams/addPlayer',
    async ({ id, playerName }) => {
        const player = await addPlayer({ id, playerName });
        return { id, player };
    }
);

export const removePlayerThunk = createAsyncThunk(
    'teams/removePlayer',
    async ({ id, playerName }) => {
        await removePlayer({ id, playerName });
        return { id, playerName };
    }
);

const {
    reducer: teamsReducer,
    actions: teamsActions,
    fetchThunk: fetchTeams,
} = makeEntitySlice({
    entityName: "teams",
    fetchAll: teamsAPI.getAll,
    extraReducers: (builder) => {
        builder
            .addCase(deleteTeamThunk.fulfilled, (state, action) => {
                state.list = state.list.filter(team => team.name !== action.payload);
            })
            .addCase(addPlayerThunk.fulfilled, (state, action) => {
                const team = state.list.find(t => t.name === action.payload.id);
                if (team && !team.players.includes(action.payload.player)) {
                    team.players.push(action.payload.player);
                }
            })
            .addCase(removePlayerThunk.fulfilled, (state, action) => {
                const team = state.list.find(t => t.name === action.payload.id);
                if (team) {
                    team.players = team.players.filter(p => p !== action.payload.playerName);
                }
            })
            .addCase(updateTeamThunk.fulfilled, (state, action) => {
                const idx = state.list.findIndex(t => t.name === action.payload.name);
                if (idx !== -1) {
                    state.list[idx] = action.payload;
                }
            });
    }
});

export const { setList: setTeams, clearList: clearTeams } = teamsActions;
export { fetchTeams };
export default teamsReducer;
