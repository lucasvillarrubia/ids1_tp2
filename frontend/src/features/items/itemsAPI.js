import axios from "axios"
import { BASE_URL } from "../../utils/constants"
import { Product } from '../../staticData/Items.jsx'
import { setProducts } from './itemsSlice.js'

export const loadProductsByGenre = (genre) => async (dispatch) => {
        try {
                const res = await axios.get(`${BASE_URL}/${genre}`);
                const data = res.data;
                const products = data.map(p => new Product(
                    p.id, p.name, p.author, p.date, p.genre, p.xAdded
                ));
                dispatch(setProducts(products));
        } catch (err) {
                console.error("Failed to fetch products by genre:", err);
        }
};
