import { ErrorMessage, Field } from 'formik';

const ItemsInput = ({ name, type, id, htmlFor, placeholder, options = [], children, multiple = false }) => {
        return (
            <div>
                    <label htmlFor={htmlFor}>{children}</label>

                    <Field name={name}>
                            {({ field, form: { setFieldValue } }) => {
                                    if (type === 'select') {
                                            return (
                                                <select
                                                    id={id}
                                                    multiple={multiple}
                                                    value={field.value || (multiple ? [] : '')}
                                                    onChange={(e) => {
                                                            const selected = Array.from(e.target.selectedOptions).map(opt => opt.value);
                                                            setFieldValue(name, multiple ? selected : selected[0]);
                                                    }}
                                                >
                                                        {!multiple && <option value="" disabled>{placeholder}</option>}
                                                        {options.map(({ value, label }) => (
                                                            <option key={value} value={value}>
                                                                    {label}
                                                            </option>
                                                        ))}
                                                </select>
                                            );
                                    }

                                    if (type === 'checkbox-group') {
                                            const currentValues = field.value || [];
                                            return (
                                                <div id={id}>
                                                        {options.map(({ value, label }) => (
                                                            <label key={value} style={{ display: 'block', marginBottom: '4px' }}>
                                                                    <input
                                                                        type="checkbox"
                                                                        name={name}
                                                                        value={value}
                                                                        checked={currentValues.includes(value)}
                                                                        onChange={() => {
                                                                                const newValues = currentValues.includes(value)
                                                                                    ? currentValues.filter(v => v !== value)
                                                                                    : [...currentValues, value];
                                                                                setFieldValue(name, newValues);
                                                                        }}
                                                                    />
                                                                    {label}
                                                            </label>
                                                        ))}
                                                </div>
                                            );
                                    }

                                    // Fallback to standard input
                                    return (
                                        <input
                                            {...field}
                                            type={type}
                                            id={id}
                                            placeholder={placeholder}
                                        />
                                    );
                            }}
                    </Field>

                    <ErrorMessage name={name}>
                            {(message) => <div style={{ color: 'red' }}>{message}</div>}
                    </ErrorMessage>
            </div>
        );
};

export default ItemsInput;
