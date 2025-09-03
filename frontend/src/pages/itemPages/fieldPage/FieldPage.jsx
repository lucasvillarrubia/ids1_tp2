import React, { useState, useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { useParams, useNavigate } from 'react-router-dom'
import { ItemPageInfo, ItemPageAuthor, ItemPageTitle, ActionButton, ButtonContainer, ExpandedItemCardUI, FieldContainer } from '../itemPagesStyles.js'
import { updateField, deleteField } from '../../../features/fields/fieldsAPI'
import ItemsForm from '../../../components/itemsForm/ItemsForm.jsx'
import {fetchFields} from "../../../features/fields/fieldsSlice.js";

const FieldPage = () => {
    const dispatch = useDispatch();
    useEffect(() => {
        dispatch(fetchFields());
    }, [dispatch]);
    const { fieldId } = useParams();
    const navigate = useNavigate();
    const [editing, setEditing] = useState(false);

    const field = useSelector(state =>
        state.fields.list.find(f => f.id === Number(fieldId))
    )

    const handleDelete = () => {
        try {
            if (confirm('Estás seguro de que querés eliminar esta cancha?')) {
                dispatch(deleteField(field.id));
                navigate('/me');
            }
        } catch (error) {
            console.error("Error al eliminar la cancha:", error);
            alert("No se pudo eliminar la cancha.");
        }
    }

    if (!field) return <p>Cancha no encontrada</p>

    let iAmOwner = field.ownerEmail === useSelector(state => state.users.currentUser.email);

    return editing ? (
        <FieldContainer>
            <ItemsForm key="updateField" itemCategory="updateField" onCancel={() => setEditing(false)} existingItem={field} />
        </FieldContainer>
    ) : (
        <FieldContainer>
            <ExpandedItemCardUI>
                <ItemPageTitle>{field.name}</ItemPageTitle>
                <ItemPageInfo>{field.description}</ItemPageInfo>
                <ButtonContainer>
                    {iAmOwner && <ActionButton onClick={() => setEditing(true)}>Editar</ActionButton>}
                    {iAmOwner && <ActionButton onClick={handleDelete}>Eliminar</ActionButton>}
                </ButtonContainer>
            </ExpandedItemCardUI>
            <ActionButton onClick={() => navigate('/me')}>Volver a mi perfíl</ActionButton>
        </FieldContainer>
    )
}

export default FieldPage
