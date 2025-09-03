import React from 'react'
import { ErrorMessage, Field } from 'formik';
import { SignupLabel, SignupInputStyled, ErrorMessageUI } from './SignupFormStyles';

const SignupInput = ({ children, htmlFor, type, id, placeholder, name }) => {
        return (
                <Field name={name}>
                        {({ field, form: { errors, touched } }) => (
                                <div>
                                        <SignupLabel htmlFor={htmlFor}>{children}</SignupLabel>
                                        <SignupInputStyled
                                                type={type}
                                                id={id}
                                                placeholder={placeholder}
                                                {...field}
                                                erroneous={errors[field.name] && touched[field.name]}
                                        ></SignupInputStyled>
                                        <ErrorMessage name={field.name}>
                                                {message => <ErrorMessageUI>{message}</ErrorMessageUI>}
                                        </ErrorMessage>
                                </div>
                        )}
                </Field>
        )
}

export default SignupInput