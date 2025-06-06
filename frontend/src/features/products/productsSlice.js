import { createSlice } from "@reduxjs/toolkit";
import { ProductCollection, ProductsByGenre } from "../../staticData/Products";

const initialState = {
        products: [],
        productsByGenre: {},
        totalProducts: 0
}

export const ProductsSlice = createSlice({
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

export const { getProducts, setProducts } = ProductsSlice.actions;

export default ProductsSlice.reducer;