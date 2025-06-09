import { createSlice } from "@reduxjs/toolkit";


const initialState = {
        currentUser: null,
        token: null,
        isAuthenticated: false,
        userMenuOnDisplay: JSON.parse(sessionStorage.getItem('userMenuOnDisplay')) || false
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
                        sessionStorage.removeItem('userMenuOnDisplay');
                        state.currentUser = null;
                        state.token = null;
                        state.isAuthenticated = false;
                        state.userMenuOnDisplay = false;
                },
                toggleUserMenuDisplay: (state) => {
                        const newValue = !state.userMenuOnDisplay;
                        sessionStorage.setItem('userMenuOnDisplay', JSON.stringify(newValue));
                        return { ...state, userMenuOnDisplay: newValue };
                }
        }
});

export const { setCurrentUser, setAuth, logout, toggleUserMenuDisplay } = usersSlice.actions;

export default usersSlice.reducer;