import { createSlice } from "@reduxjs/toolkit";


const initialState = {
        currentUser: null,
        userMenuOnDisplay: false
}

const usersSlice = createSlice({
        name: 'users',
        initialState,
        reducers: {
                setCurrentUser: (state, action) => {return { ...state, currentUser: action.payload }},
                toggleUserMenuDisplay: state => {return {...state, userMenuOnDisplay: !state.userMenuOnDisplay}}
        }
});

export const { setCurrentUser, toggleUserMenuDisplay } = usersSlice.actions;

export default usersSlice.reducer;