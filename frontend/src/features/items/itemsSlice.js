import { createSlice } from "@reduxjs/toolkit";

const initialState = {
        items: [],
        itemsByGenre: {},
        totalItems: 0
}

export const itemsSlice = createSlice({
        name: 'items',
        initialState,
        reducers: {
                getItems: state => state,
                setItems(state, action) {
                        state.items = action.payload;
                        state.totalItems = action.payload.length;
                }
        }
});

export const { getItems, setItems } = itemsSlice.actions;

export default itemsSlice.reducer;