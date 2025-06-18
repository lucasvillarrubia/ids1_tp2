import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";

export const makeEntitySlice = ({ entityName, fetchAll }) => {
    const fetchThunk = createAsyncThunk(`${entityName}/fetchAll`, async () => {
        return await fetchAll();
    });

    const slice = createSlice({
        name: entityName,
        initialState: {
            list: [],
            status: "idle",
            error: null,
        },
        reducers: {
            setList(state, action) {
                state.list = action.payload;
            },
            clearList(state) {
                state.list = [];
            },
        },
        extraReducers: (builder) => {
            builder
                .addCase(fetchThunk.pending, (state) => {
                    state.status = "loading";
                })
                .addCase(fetchThunk.fulfilled, (state, action) => {
                    state.status = "succeeded";
                    state.list = action.payload;
                })
                .addCase(fetchThunk.rejected, (state, action) => {
                    state.status = "failed";
                    state.error = action.error.message;
                });
        },
    });

    return {
        reducer: slice.reducer,
        actions: slice.actions,
        fetchThunk,
    };
};
