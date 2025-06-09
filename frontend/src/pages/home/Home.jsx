import React from 'react'
import Hero from '../../components/hero/Hero'
import Categories from '../../components/categories/Categories'
import Items from '../../components/items/Items.jsx'

const Home = () => {
  return (
        <>
                <Hero />
                <Categories fromPage='home' />
                <Items />
        </>
  )
}

export default Home