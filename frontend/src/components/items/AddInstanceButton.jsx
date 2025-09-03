import React from 'react'
import { AddInstanceButtonUI } from './ItemStyles.js';
import { useDispatch } from 'react-redux';

const AddInstanceButton = ({ onClick }) => {
        const dispatch = useDispatch();
        return (
                <AddInstanceButtonUI onClick={onClick}>
                        CREAR
                </AddInstanceButtonUI>
        )
}

export default AddInstanceButton