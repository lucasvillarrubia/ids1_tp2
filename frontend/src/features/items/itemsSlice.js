import { createSlice } from "@reduxjs/toolkit";

const initialState = {
        products: [],
        productsByGenre: {},
        totalProducts: 0
}

export const ItemsSlice = createSlice({
        name: 'products',
        initialState,
        reducers: {
                getProducts: state => state,
                setProducts(state, action) {
                        state.products = action.payload;
                        state.totalProducts = action.payload.length;
                }
        }
});

export const { getProducts, setProducts } = ItemsSlice.actions;

export default ItemsSlice.reducer;