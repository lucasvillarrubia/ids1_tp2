import React, { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom';
import { ItemCardsContainer, ItemsSectionButton, ButtonContainer } from "./ItemStyles.js";
import ItemCard from "./ItemCard.jsx";
import { HomeTitle, HomeSection } from "../../pages/home/HomeStyles";
import { useSelector, useDispatch } from "react-redux";
import { INITIAL_LIMIT } from "../../utils/index.js"
import AddInstanceButton from "./AddInstanceButton";
import { loadItemsByGenre } from "../../features/items/itemsAPI.js";
import { setItems } from "../../features/items/itemsSlice.js";

const Items = () => {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    let renderCount = 0;
    let items = useSelector(state => state.items.items);
    const selectedCat = useSelector(state => state.categories.selectedCategory);
    const [limit, setLimit] = useState(INITIAL_LIMIT);

    useEffect(() => {
        if (selectedCat) {
            dispatch(loadItemsByGenre(selectedCat));
        } else {
            dispatch(setItems([]));
        }
    }, [selectedCat]);

    useEffect(() => {
        setLimit(INITIAL_LIMIT);
    }, [selectedCat]);

  return (
    <HomeSection>
        {selectedCat && (<HomeTitle>RESULTADOS</HomeTitle>)}
        {selectedCat && (<ItemCardsContainer>
              {items.length > 0 ? (
                  items.slice(0, limit).map(item => (
                      <ItemCard key={item.id} {...item} />
                  ))
              ) : (
                  <p>Todavía no hay resultados de esto. Creá uno!</p>
              )}
            <AddInstanceButton onClick={() => {
                console.log('Navigating to create page');
                console.log('Selected Category:', selectedCat);
                navigate('/create');
            }}/>
          </ItemCardsContainer>)}
        {!selectedCat && (<HomeTitle>MIRÁ LO QUE HAY, SELECCIONÁ UNA CATEGORÍA</HomeTitle>)}
    </HomeSection>
  );
};

export default Items;
