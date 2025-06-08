import React from 'react'
import ItemsForm from '../../components/itemsForm/ItemsForm.jsx'
import { CreateBG, CreateContainer, CreateTitle, CreateSubtitle, CreateInfo } from './CreateStyles'


const Create = () => {
        return (
                <CreateBG>
                        <CreateTitle>Terminá tu compra</CreateTitle>
                        <CreateContainer>
                                <CreateInfo>
                                        <CreateSubtitle>Completá la información de envío</CreateSubtitle>
                                        <ItemsForm />
                                </CreateInfo>
                        </CreateContainer>
                </CreateBG>
        )
}

export default Create