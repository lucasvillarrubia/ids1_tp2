import React, { useState } from 'react';
import { Form, Formik, ItemSubmit } from './ItemsFormStyles.js';
import ItemsInput from './ItemsInput.jsx';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { getFormConfig, getFormFields } from "../../formik/index.js";
import { createItem, loadItemsByGenre } from "../../features/items/itemsAPI.js";
import { updateField } from "../../features/fields/fieldsAPI.js";
import { updateMatch } from "../../features/matches/matchesAPI.js";
import { updateTeamThunk } from "../../features/teams/teamsSlice.js";
import FlashMessage from "../globalComponents/FlashMessage/FlashMessage.jsx";


const ItemsForm = ({ itemCategory, onCancel, existingItem }) => {
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const [flash, setFlash] = useState(null);

    const { initialValues, validationSchema } = (itemCategory.includes('update'))
        ? getFormConfig(itemCategory)
        : getFormConfig(itemCategory, existingItem);
    const formFields = getFormFields(itemCategory);

    const showFlash = (msg) => {
        setFlash({
            message: msg,
            type: 'error',
            onClose: () => setFlash(null),
        });
    };

    const handleCreateSubmit = async (values, { setSubmitting }) => {
        try {
            const realCategory = itemCategory === 'closed' ? 'matches' : itemCategory;
            console.log("Submiting values: ", values);
            await createItem(realCategory, values);
            await loadItemsByGenre(dispatch);
            navigate('/congratulations');
        } catch (error) {
            console.error(error);
            showFlash('No se pudo crear el ítem: error visible en consola');
        } finally {
            setSubmitting(false);
        }
    };

    const handleUpdateFieldSubmit = async (values) => {
        try {
            console.log("Intentando actualizar con", values);
            await dispatch(updateField(existingItem.id, values));
            onCancel();
            navigate('/me');
        } catch (error) {
            console.error(error);
            showFlash('No se pudo actualizar la cancha: error visible en consola');
        }
    };

    const handleUpdateMatchSubmit = async (values) => {
        try {
            console.log("Intentando actualizar partido con", values);
            await dispatch(updateMatch(existingItem.id, values));
            onCancel();
            navigate('/me');
        } catch (error) {
            console.error(error);
            showFlash('No se pudo actualizar el partido: error visible en consola');
        }
    };

    const handleUpdateTeamSubmit = async (values) => {
        try {
            console.log("Intentando actualizar equipo con", values);
            await dispatch(updateTeamThunk({ id: existingItem.name, teamData: values }));
            onCancel();
            navigate('/me');
        } catch (error) {
            console.error("Error al actualizar el equipo:", error);
            showFlash('No se pudo actualizar el equipo: error visible en consola');
        }
    };

    let handleSubmit;
    switch (itemCategory) {
        case 'updateField':
            handleSubmit = handleUpdateFieldSubmit;
            break;
        case 'updateMatch':
            handleSubmit = handleUpdateMatchSubmit;
            break;
        case 'updateTeam':
            handleSubmit = handleUpdateTeamSubmit;
            break;
        default:
            handleSubmit = handleCreateSubmit;
            break;
    }

    return (
        <>
            <Formik
                initialValues={initialValues}
                validationSchema={validationSchema}
                onSubmit={handleSubmit}
            >
                {({ isSubmitting }) => (
                    <Form>
                        {formFields.map(field => (
                            <ItemsInput key={field.name} {...field}>
                                {field.label}
                            </ItemsInput>
                        ))}
                        <ItemSubmit type='submit' disabled={isSubmitting}>
                            {(itemCategory.includes('update')) ? 'ACTUALIZAR' : 'CREAR'}
                        </ItemSubmit>
                        {(itemCategory.includes('update')) &&
                            <ItemSubmit type='button' onClick={onCancel} disabled={isSubmitting}>
                                CANCELAR
                            </ItemSubmit>
                        }
                    </Form>
                )}
            </Formik>

            {flash && (
                <FlashMessage
                    message={flash.message}
                    type={flash.type}
                    onClose={flash.onClose}
                />
            )}
        </>
    );
};

export default ItemsForm;

