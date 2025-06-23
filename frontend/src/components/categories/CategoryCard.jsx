import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import { CategoryCardUI, CategorySymbol, CategoryName } from './CategoryStyles'
import { IconByCat } from '../../staticData/Categories'
import { useSelector, useDispatch } from 'react-redux';
import { selectCategory } from '../../features/categories/categoriesSlice';
import FlashMessage from "../globalComponents/FlashMessage/FlashMessage.jsx";

const CategoryCard = ({ name, category }) => {
  const selected = useSelector(state => state.categories.selectedCategory);
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { currentUser } = useSelector(state => state.users);

    const [showFlash, setShowFlash] = useState(false);

    const handleCategoryClick = () => {
        if (!currentUser) {
            setShowFlash(true);
        } else {
            dispatch(selectCategory(category));
        }
    };

  return (
      <>
          <CategoryCardUI selected={category === selected} onClick={handleCategoryClick}>
              <CategorySymbol>{IconByCat[category]}</CategorySymbol>
              <CategoryName>{name}</CategoryName>
          </CategoryCardUI>
          {showFlash && (
              <FlashMessage
                  message="Tenés que iniciar sesión para realizar esta acción!"
                  onClose={() => setShowFlash(false)}
                  redirect="/login"
              />
          )}
      </>
  )
}

export default CategoryCard