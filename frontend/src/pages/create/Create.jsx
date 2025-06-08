import React from 'react'
import ItemsForm from '../../components/itemsForm/ItemsForm.jsx'
import { CreateBG, CreateContainer, CreateTitle, CreateSubtitle, CreateInfo } from './CreateStyles'
import Categories from "../../components/categories/Categories.jsx";
import { useSelector } from "react-redux";


const Create = () => {
    const selectedCat = useSelector(state => state.categories.selectedCategory);

        return (
                <CreateBG>
                        <CreateTitle>Nuevo Item</CreateTitle>
                        <CreateContainer>
                                <CreateInfo>
                                        <CreateSubtitle>Completá el formulario</CreateSubtitle>
                                        <Categories fromPage='create' />
                                        <ItemsForm key={selectedCat.category} itemCategory={selectedCat.category}/>
                                </CreateInfo>
                        </CreateContainer>
                </CreateBG>
        )
}

export default Create