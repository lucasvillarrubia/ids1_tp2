import React from 'react'
import { Form, Formik, ItemSubmit } from './ItemsFormStyles.js'
import { createInitialValues } from '../../formik/initialValues'
import { createValidationSchema } from '../../formik/validationSchema'
import ItemsInput from './ItemsInput.jsx'
import { useDispatch, useSelector } from 'react-redux'
import { useNavigate } from 'react-router-dom'

const ItemsForm = () => {
        const dispatch = useDispatch();
        const navigate = useNavigate();
        const { currentUser } = useSelector(state => state.users);
        return (
                <Formik 
                        initialValues={createInitialValues}
                        validationSchema={createValidationSchema}
                        onSubmit={
                                async values => {
                                        try {
                                                // await createItem para crear el item
                                                // await getItems para actualizar la lista de items
                                                navigate('/congratulations');
                                        } catch (error) {
                                                alert('No se pudo crear la orden');
                                        }
                                }
                        }
                >
                        {({isSubmitting}) => (
                                <Form>
                                        <ItemsInput name='name' type='text' id='nombre' htmlFor='nombre' placeholder='Tu nombre completo'>
                                                Nombre
                                        </ItemsInput>
                                        <ItemSubmit type='submit' disabled={!cartItems.length || isSubmitting}>Continuar al pago</ItemSubmit>
                                </Form>
                        )}
                </Formik>
        )
}

export default ItemsForm