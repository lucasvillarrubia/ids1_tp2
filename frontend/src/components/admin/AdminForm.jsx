import React, { useState } from 'react'
import { Field } from 'formik'
import { SelectWrapper, ResponseBox, AdminSubmit, Form, Formik, AdminWrapper } from './AdminFormStyles.js'
import { endpoints, methods, sendRequest } from "./AdminNeeds.js";


const AdminForm = () => {
    const [apiResponse, setApiResponse] = useState('');

    return (
        <Formik
            initialValues={{
                endpoint: endpoints[0].value,
                method: 'POST',
                jsonInput: '',
            }}
            onSubmit={async (values, { setSubmitting }) => {
                try {
                    const result = await sendRequest(values);
                    setApiResponse(result);
                } catch (e) {
                    setApiResponse(`❌ Falló la request: ${e.message}`);
                } finally {
                    setSubmitting(false);
                }
            }}
        >
            {({ values, isSubmitting, handleChange }) => (
                <AdminWrapper>
                    <Form>
                        <SelectWrapper>
                            <label>
                                Endpoint:
                                <Field as="select" name="endpoint">
                                    {endpoints.map(ep => (
                                        <option key={ep.value} value={ep.value}>{ep.label}</option>
                                    ))}
                                </Field>
                            </label>

                            <label>
                                Method:
                                <Field as="select" name="method">
                                    {methods.map(m => (
                                        <option key={m} value={m}>{m}</option>
                                    ))}
                                </Field>
                            </label>
                        </SelectWrapper>

                        {(values.method === 'POST' || values.method === 'PATCH') && (
                            <>
                                <label htmlFor="jsonInput" style={{fontSize: '40px', fontFamily: 'Bebas Neue'}}>JSON Payload</label>
                                <Field
                                    as="textarea"
                                    id="jsonInput"
                                    name="jsonInput"
                                    placeholder='Acá va el JSON'
                                    rows={20}
                                    style={{
                                        width: '70%',
                                        padding: '20px',
                                        fontFamily: 'monospace',
                                        fontSize: '15px',
                                        borderRadius: '5px',
                                        border: '2px solid #000',
                                        resize: 'vertical',
                                    }}
                                />
                            </>
                        )}

                        <AdminSubmit type='submit' disabled={isSubmitting}>
                            SEND
                        </AdminSubmit>
                    </Form>

                    {apiResponse && <ResponseBox>{apiResponse}</ResponseBox>}
                </AdminWrapper>
            )}
        </Formik>
    )
}

export default AdminForm
