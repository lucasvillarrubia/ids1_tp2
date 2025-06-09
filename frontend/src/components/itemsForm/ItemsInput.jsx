import React from 'react'
import { ErrorMessage, Field, FieldArray } from 'formik';
import {ItemLabel, ItemInputStyled, ErrorMessageUI, ArrayButton} from './ItemsFormStyles.js';

const ItemsInput = ({ children, htmlFor, type, id, placeholder, name, isList = false }) => {
        return (
            <div>
                    <ItemLabel htmlFor={htmlFor}>{children}</ItemLabel>

                    {isList ? (
                        <FieldArray name={name}>
                                {({ push, remove, form }) => (
                                    <>
                                            {form.values[name]?.map((value, index) => (
                                                <div key={index} style={{ display: 'flex', gap: '8px', alignItems: 'center', flexDirection: 'row' }}>
                                                        <Field
                                                            name={`${name}[${index}]`}
                                                            placeholder={`${placeholder} #${index + 1}`}
                                                            as={ItemInputStyled}
                                                            type={type}
                                                        />
                                                        <ArrayButton type="button" onClick={() => remove(index)}>🗑️</ArrayButton>
                                                </div>
                                            ))}
                                            <ArrayButton type="button" onClick={() => push('')}>+ Agregar</ArrayButton>

                                            <ErrorMessage name={name}>
                                                    {msg => <ErrorMessageUI>{msg}</ErrorMessageUI>}
                                            </ErrorMessage>
                                    </>
                                )}
                        </FieldArray>
                    ) : (
                        <Field name={name}>
                                {({ field, form: { errors, touched } }) => (
                                    <>
                                            <ItemInputStyled
                                                type={type}
                                                id={id}
                                                placeholder={placeholder}
                                                {...field}
                                                erroneous={errors[field.name] && touched[field.name]}
                                            />
                                            <ErrorMessage name={field.name}>
                                                    {msg => <ErrorMessageUI>{msg}</ErrorMessageUI>}
                                            </ErrorMessage>
                                    </>
                                )}
                        </Field>
                    )}
            </div>
        )
}

export default ItemsInput