import React from 'react'
import ItemsForm from '../../components/itemsForm/ItemsForm.jsx'
import { CreateBG, CreateContainer, CreateTitle, CreateSubtitle, CreateInfo } from './CreateStyles'
import Categories from "../../components/categories/Categories.jsx";
import { useSelector } from "react-redux";


const Create = () => {
    let selectedCat = useSelector(state => state.categories.selectedCategory);
    if (!selectedCat) {
        selectedCat = 'teams';
    }
        return (
                <CreateBG>
                        <CreateTitle>Nuevo Item</CreateTitle>
                        <CreateContainer>
                                <Categories fromPage='create' />
                                <CreateInfo>
                                        <CreateSubtitle>Completá el formulario</CreateSubtitle>
                                        <ItemsForm key={selectedCat} itemCategory={selectedCat}/>
                                </CreateInfo>
                        </CreateContainer>
                </CreateBG>
        )
}

export default Create