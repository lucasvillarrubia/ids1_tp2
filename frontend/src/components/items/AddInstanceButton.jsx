import React from 'react'
import { AddInstanceButtonUI } from './ItemStyles.js';
import { useDispatch } from 'react-redux';

const AddInstanceButton = () => {
        const dispatch = useDispatch();
        return (
                <AddInstanceButtonUI /*onClick={() => dispatch(addToCart())}*/>
                        CREAR
                </AddInstanceButtonUI>
        )
}

export default AddInstanceButton