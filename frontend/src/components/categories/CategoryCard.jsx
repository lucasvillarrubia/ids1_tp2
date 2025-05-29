import React from 'react'
import { CategoryCardUI, CategorySymbol, CategoryName } from './CategoryStyles'
import { IconByCat } from '../../data/Categories'
import { useSelector, useDispatch } from 'react-redux';
import { selectCategory } from '../../features/categories/categoriesSlice';

const CategoryCard = ({ name, category }) => {
  const selected = useSelector(state => state.categories.selectedCategory);
  const dispatch = useDispatch();
  return (
    <CategoryCardUI selected={category === selected} onClick={() => dispatch(selectCategory(category))}>
        <CategorySymbol>{IconByCat[category]}</CategorySymbol>
        <CategoryName>{name}</CategoryName>
    </CategoryCardUI>
  )
}

export default CategoryCard