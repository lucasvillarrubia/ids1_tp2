import {fieldInitialValues, matchInitialValues, teamInitialValues} from "./initialValues.js";
import {fieldValidationSchema, matchValidationSchema, teamValidationSchema} from "./validationSchema.js";
import {fieldFields, matchFields, teamFields} from "./itemsFields.js";

export const getFormConfig = (type) => {
    switch (type) {
        case 'team':
            return {
                initialValues: teamInitialValues,
                validationSchema: teamValidationSchema,
            };
        case 'field':
            return {
                initialValues: fieldInitialValues,
                validationSchema: fieldValidationSchema
            };
        case 'match':
            return {
                initialValues: matchInitialValues,
                validationSchema: matchValidationSchema
            }
        default:
            throw new Error(`Unknown form type: ${type}`);
    }
};

export const getFormFields = (type) => {
    switch (type) {
        case 'team':
            return teamFields;
        case 'field':
            return fieldFields;
        case 'match':
            return matchFields;
        default:
            throw new Error(`Unknown form type for fields: ${type}`);
    }
}
