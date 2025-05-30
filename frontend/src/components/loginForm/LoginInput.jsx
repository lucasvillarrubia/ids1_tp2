import React from 'react'
import { ErrorMessage, Field } from 'formik';
import { LoginLabel, LoginInputStyled, ErrorMessageUI } from './LoginFormStyles';

const LoginInput = ({ children, htmlFor, type, id, placeholder, name }) => {
        return (
                <Field name={name}>
                        {({ field, form: { errors, touched } }) => (
                                <div>
                                        <LoginLabel htmlFor={htmlFor}>{children}</LoginLabel>
                                        <LoginInputStyled
                                                type={type}
                                                id={id}
                                                placeholder={placeholder}
                                                {...field}
                                                erroneous={errors[field.name] && touched[field.name]}
                                        ></LoginInputStyled>
                                        <ErrorMessage name={field.name}>
                                                {message => <ErrorMessageUI>{message}</ErrorMessageUI>}
                                        </ErrorMessage>
                                </div>
                        )}
                </Field>
        )
}

export default LoginInput