import React, { useEffect, useState } from "react";
import { ProductCardsContainer, ProductsSectionButton, ButtonContainer } from "./ProductStyles";
import ProductCard from "./ProductCard";
import { HomeTitle, HomeSection } from "../../pages/home/HomeStyles";
import { useSelector } from "react-redux";
import { INITIAL_LIMIT } from "../../utils/constants"
import AddInstanceButton from "./AddInstanceButton";

const Products = () => {
  let renderCount = 0;
  let products = useSelector(state => state.products.productsByGenre);
  const selectedCat = useSelector(state => state.categories.selectedCategory);
  const [limit, setLimit] = useState(INITIAL_LIMIT);
  if (selectedCat && products[selectedCat]) {
    products = { [selectedCat]: products[selectedCat] };
  }
  else if (selectedCat && !products[selectedCat]) {
    products = [];
  }
  useEffect(() => {
      setLimit(INITIAL_LIMIT);
      renderCount = 0;
    }, [selectedCat]);

  return (
    <HomeSection>
      <HomeTitle>RESULTADOS</HomeTitle>
      <ProductCardsContainer>
        {
          Object.entries(products).map(([_genreId, prodsByGenre]) => (
            prodsByGenre.map((product) => {
              if (limit > renderCount) {
                renderCount ++;
                return (<ProductCard key={product.id} {...product}/>);
              }
              else {
                return null;
              }
            })
          ))
        }
        <AddInstanceButton />
        {renderCount == 0 && <p>Todavía no hay resultados de esto. Creá uno!</p>}
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
