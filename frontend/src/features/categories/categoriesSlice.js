import { createSlice } from "@reduxjs/toolkit";
import { CategoryCollection } from "../../data/Categories";

const initialState = {
        categories: CategoryCollection,
        selectedCategory: null
}

export const CategoriesSlice = createSlice({
        name: 'categories',
        initialState,
        reducers: {
                getCategories: state => state,
                selectCategory: (state, action) => {
                        return { 
                                ...state, 
                                selectedCategory: action.payload !== state.selectedCategory ? action.payload : null
                        }
                }
        }
});

export const { getCategories, selectCategory } = CategoriesSlice.actions;

export default CategoriesSlice.reducer;