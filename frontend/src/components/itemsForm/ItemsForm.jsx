import React from 'react'
import { Form, Formik, ItemSubmit } from './ItemsFormStyles.js'
import ItemsInput from './ItemsInput.jsx'
import { useDispatch } from 'react-redux'
import { useNavigate } from 'react-router-dom'
import {getFormConfig, getFormFields} from "../../formik/index.js";
import {createItem, loadItemsByGenre} from "../../features/items/itemsAPI.js";

const ItemsForm = ({ itemCategory }) => {
        const dispatch = useDispatch();
        const navigate = useNavigate();
        const { initialValues, validationSchema } = getFormConfig(itemCategory);
        const formFields = getFormFields(itemCategory);

        const handleSubmit = async (values, { setSubmitting }) => {
            try {
                console.log("📦 Form output:", JSON.stringify(values, null, 2));
                await createItem(itemCategory, values);
                await loadItemsByGenre(dispatch);
                navigate('/congratulations');
            } catch (error) {
                alert('No se pudo crear el ítem: error visible en consola');
            } finally {
                setSubmitting(false);
            }
        };

        return (
                <Formik
                        initialValues={initialValues}
                        validationSchema={validationSchema}
                        onSubmit={handleSubmit}
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
                                                {...field}
                                            >
                                                {field.label}
                                            </ItemsInput>
                                        ))}
                                        <ItemSubmit type='submit' disabled={isSubmitting}>CREAR</ItemSubmit>
                                </Form>
                        )}
                </Formik>
        )
}

export default ItemsForm