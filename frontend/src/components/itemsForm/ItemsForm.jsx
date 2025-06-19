import React from 'react'
import { Form, Formik, ItemSubmit } from './ItemsFormStyles.js'
import ItemsInput from './ItemsInput.jsx'
import { useDispatch } from 'react-redux'
import { useNavigate } from 'react-router-dom'
import {getFormConfig, getFormFields} from "../../formik/index.js";
import {createItem, loadItemsByGenre} from "../../features/items/itemsAPI.js";
import {updateField} from "../../features/fields/fieldsAPI.js";

const ItemsForm = ({ itemCategory, onCancel, existingItem }) => {
        const dispatch = useDispatch();
        const navigate = useNavigate();
        const { initialValues, validationSchema } = (itemCategory === 'updateField') ? getFormConfig(itemCategory) : getFormConfig(itemCategory, existingItem);
        const formFields = getFormFields(itemCategory);

        const handleCreateSubmit = async (values, { setSubmitting }) => {
            try {
                if (itemCategory === 'closed') {
                    console.log("Intentando crear partido cerrado")
                    itemCategory = 'matches';
                }
                console.log("Submiting values: ", values)
                await createItem(itemCategory, values);
                await loadItemsByGenre(dispatch);
                navigate('/congratulations');
            } catch (error) {
                alert('No se pudo crear el ítem: error visible en consola');
            } finally {
                setSubmitting(false);
            }
        };

        const handleUpdateSubmit = (values) => {
            try {
                console.log("Intentando actualizar con", values);
                dispatch(updateField(existingItem.id, values));
                onCancel();
                navigate('/me')
            } catch (error) {
                alert('No se pudo actualizar el ítem: error visible en consola');
            }

        }

        return (
                <Formik
                        initialValues={initialValues}
                        validationSchema={validationSchema}
                        onSubmit={itemCategory === 'updateField' ? handleUpdateSubmit : handleCreateSubmit}
                >
                        {({isSubmitting}) => (
                                <Form>
                                        {formFields.map(field => (
                                            <ItemsInput
                                                // key={field.name}
                                                // name={field.name}
                                                // type={field.type}
                                                // id={field.id}
                                                // htmlFor={field.htmlFor}
                                                // placeholder={field.placeholder}
                                                // options={field.options}
                                                {...field}
                                            >
                                                {field.label}
                                            </ItemsInput>

                                        ))}
                                        <ItemSubmit type='submit' disabled={isSubmitting}>
                                            {(itemCategory === 'updateField') ? 'ACTUALIZAR' : 'CREAR'}
                                        </ItemSubmit>
                                    {(itemCategory === 'updateField') &&
                                        <ItemSubmit type='button' onClick={onCancel} disabled={isSubmitting}>CANCELAR</ItemSubmit>
                                    }
                                </Form>
                        )}
                </Formik>
        )
}

export default ItemsForm