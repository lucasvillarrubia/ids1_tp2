import React from 'react'
import { ErrorMessage, Field } from 'formik';
import { ItemLabel, ItemInputStyled, ErrorMessageUI } from './ItemsFormStyles.js';

const ItemsInput = ({ children, htmlFor, type, id, placeholder, name }) => {
        return (
                <Field name={name}>
                        {({ field, form: { errors, touched } }) => (
                                <div>
                                        <ItemLabel htmlFor={htmlFor}>{children}</ItemLabel>
                                        <ItemInputStyled
                                                type={type}
                                                id={id}
                                                placeholder={placeholder}
                                                {...field}
                                                erroneous={errors[field.name] && touched[field.name]}
                                        ></ItemInputStyled>
                                        <ErrorMessage name={field.name}>
                                                {message => <ErrorMessageUI>{message}</ErrorMessageUI>}
                                        </ErrorMessage>
                                </div>
                        )}
                </Field>
        )
}

export default ItemsInput