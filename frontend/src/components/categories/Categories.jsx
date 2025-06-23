import React from 'react'
import { HomeSection, HomeTitle } from '../../pages/home/HomeStyles';
import CategoryCard from './CategoryCard';
import { CategoryCardsContainer } from './CategoryStyles';
import { useSelector } from 'react-redux';

const Categories = ({ fromPage }) => {
  const categories = useSelector(state => state.categories.categories);
  return (
    <HomeSection>
      <HomeTitle>{(fromPage === 'create') ? "Elegí entre estas categorías" : "FEED"}</HomeTitle>
      <CategoryCardsContainer>
        {
            categories
                .filter(category => (fromPage !== 'create' && category.name !== 'Partidos Cerrados') || fromPage === 'create')
                .map(category => <CategoryCard key={category.id} {...category} />)
        }
      </CategoryCardsContainer>
    </HomeSection>
  )
}

export default Categories