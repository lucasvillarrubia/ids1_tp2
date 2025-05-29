import React from 'react'
import { ProductCardUI, ProductTitle, ProductInfo, ProductAuthor, ProductPrice, ProductButton } from './ProductStyles';
import { useDispatch } from 'react-redux';
import {formatDate} from "../../utils/formatDate.js";

const ProductCard = (product) => {
        const dispatch = useDispatch();
        return (
                <ProductCardUI>
                        <ProductInfo>
                                <ProductAuthor>
                                        #{product.id}
                                </ProductAuthor>
                                <ProductTitle>
                                        {product.name}
                                </ProductTitle>
                                <ProductPrice>
                                        Creado el {formatDate(product.date)}
                                </ProductPrice>
                        </ProductInfo>
                        {/* <ProductButton onClick={() => dispatch(addToCart(product))}>
                                AGREGAR AL CARRITOP
                        </ProductButton> */}
                </ProductCardUI>
        )
}

export default ProductCard