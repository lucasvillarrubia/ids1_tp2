import React, { useEffect, useState } from "react";
import { ProductCardsContainer, ProductsSectionButton, ButtonContainer } from "./ProductStyles";
import ProductCard from "./ProductCard";
import { HomeTitle, HomeSection } from "../../pages/home/HomeStyles";
import { useSelector, useDispatch } from "react-redux";
import { INITIAL_LIMIT } from "../../utils/constants"
import AddInstanceButton from "./AddInstanceButton";
import { loadProductsByGenre } from "../../features/products/productsAPI.js";

const Products = () => {
    const dispatch = useDispatch();
    let renderCount = 0;
    let products = useSelector(state => state.products.products);
    const selectedCat = useSelector(state => state.categories.selectedCategory);
    const [limit, setLimit] = useState(INITIAL_LIMIT);
    // if (selectedCat && products[selectedCat]) {
    // products = { [selectedCat]: products[selectedCat] };
    // }
    // else if (selectedCat && !products[selectedCat]) {
    // products = [];
    // }

    useEffect(() => {
        if (selectedCat) {
            dispatch(loadProductsByGenre(selectedCat));
        }
    }, [selectedCat]);

    useEffect(() => {
        setLimit(INITIAL_LIMIT);
    }, [selectedCat]);

  return (
    <HomeSection>
      <HomeTitle>RESULTADOS</HomeTitle>
      <ProductCardsContainer>
          {products.length > 0 ? (
              products.slice(0, limit).map(product => (
                  <ProductCard key={product.id} {...product} />
              ))
          ) : (
              <p>Todavía no hay resultados de esto. Creá uno!</p>
          )}
        <AddInstanceButton />
      </ProductCardsContainer>
      {/* {(renderCount >= INITIAL_LIMIT) && (
        <ButtonContainer>
          <ProductsSectionButton
            onClick={() => setLimit(previous => previous - INITIAL_LIMIT)}
            disabled={limit === INITIAL_LIMIT}
          >
            Ver menos
          </ProductsSectionButton>
          <ProductsSectionButton
            onClick={() => setLimit(previous => previous + INITIAL_LIMIT)}
            disabled={(renderCount % INITIAL_LIMIT) !== 0}
          >
            Más productos
          </ProductsSectionButton>
        </ButtonContainer>
      )} */}
    </HomeSection>
  );
};

export default Products;
