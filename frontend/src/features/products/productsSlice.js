import { createSlice } from "@reduxjs/toolkit";
import { ProductCollection, ProductsByGenre } from "../../staticData/Products";

const initialState = {
        products: ProductCollection,
        productsByGenre: ProductsByGenre,
        totalProducts: ProductCollection.length
}

export const ProductsSlice = createSlice({
        name: 'products',
        initialState,
        reducers: {
                getProducts: state => state
        }
});

export const { getProducts } = ProductsSlice.actions;

export default ProductsSlice.reducer;