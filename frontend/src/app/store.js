import { combineReducers, configureStore, getDefaultMiddleware } from "@reduxjs/toolkit";
import persistReducer from "redux-persist/es/persistReducer";
import persistStore from "redux-persist/es/persistStore";
import storage from "redux-persist/lib/storage";
import categoriesReducer from "../features/categories/categoriesSlice";
import itemsReducer from "../features/items/itemsSlice.js";
import usersReducer from "../features/users/usersSlice";
import teamsReducer from "../features/teams/teamsSlice.js";
import fieldsReducer from "../features/fields/fieldsSlice.js";
import matchesReducer from "../features/matches/matchesSlice.js";
import tournamentsReducer from "../features/tournaments/tournamentsSlice.js";

const reducers = combineReducers({
        categories: categoriesReducer,
        items: itemsReducer,
        users: usersReducer,
        teams: teamsReducer,
        fields: fieldsReducer,
        matches: matchesReducer,
        tournaments: tournamentsReducer
})

const persistConfig = { key: 'root', storage, whitelist: ['users'] }

const persistedReducer = persistReducer(persistConfig, reducers);

export const store = configureStore({
        reducer: persistedReducer,
        middleware: getDefaultMiddleware => getDefaultMiddleware({ serializableCheck: false })
});

export const persistor = persistStore(store);