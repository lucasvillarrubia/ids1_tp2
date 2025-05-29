import React from 'react'
import { HomeSection, HomeTitle } from '../../pages/home/HomeStyles';
import CategoryCard from './CategoryCard';
import { CategoryCardsContainer } from './CategoryStyles';
import { useSelector } from 'react-redux';

const Categories = () => {
  const categories = useSelector(state => state.categories.categories);
  return (
    <HomeSection>
      <HomeTitle>MIRÁ LO QUE HAY</HomeTitle>
      <CategoryCardsContainer>
        {
          categories.map((category) => <CategoryCard key={category.id} {...category} />)
        }
      </CategoryCardsContainer>
    </HomeSection>
  )
}

export default Categories