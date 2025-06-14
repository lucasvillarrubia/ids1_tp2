import { createSlice } from "@reduxjs/toolkit";


const initialState = {
        currentUser: null,
        token: null,
        isAuthenticated: false,
        userMenuOnDisplay: false
}

const usersSlice = createSlice({
        name: 'users',
        initialState,
        reducers: {
                setCurrentUser: (state, action) => {return { ...state, currentUser: action.payload }},
                setAuth: (state, action) => {
                        state.currentUser = action.payload.user;
                        state.token = action.payload.token;
                        state.isAuthenticated = true;
                },
                logout: (state) => {
                        state.currentUser = null;
                        state.token = null;
                        state.isAuthenticated = false;
                },
                toggleUserMenuDisplay: state => {return {...state, userMenuOnDisplay: !state.userMenuOnDisplay}}
        }
});

export const { setCurrentUser, setAuth, logout, toggleUserMenuDisplay} = usersSlice.actions;

export default usersSlice.reducer;