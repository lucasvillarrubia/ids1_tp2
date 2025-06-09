import { combineReducers, configureStore, getDefaultMiddleware } from "@reduxjs/toolkit";
import persistReducer from "redux-persist/es/persistReducer";
import persistStore from "redux-persist/es/persistStore";
import storage from "redux-persist/lib/storage";
import categoriesReducer from "../features/categories/categoriesSlice";
import itemsReducer from "../features/items/itemsSlice.js";
import usersReducer from "../features/users/usersSlice";

const reducers = combineReducers({
        categories: categoriesReducer,
        items: itemsReducer,
        users: usersReducer
})

const persistConfig = { key: 'root', storage, whitelist: ['users'] }

const persistedReducer = persistReducer(persistConfig, reducers);

export const store = configureStore({
        reducer: persistedReducer,
        middleware: getDefaultMiddleware => getDefaultMiddleware({ serializableCheck: false })
});

export const persistor = persistStore(store);