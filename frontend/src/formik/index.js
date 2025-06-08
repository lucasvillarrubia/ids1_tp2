import {fieldInitialValues, matchInitialValues, teamInitialValues} from "./initialValues.js";
import {fieldValidationSchema, matchValidationSchema, teamValidationSchema} from "./validationSchema.js";
import {fieldFields, matchFields, teamFields} from "./itemsFields.js";

export const getFormConfig = (type) => {
    switch (type) {
        case 'teams':
            return {
                initialValues: teamInitialValues,
                validationSchema: teamValidationSchema,
            };
        case 'fields':
            return {
                initialValues: fieldInitialValues,
                validationSchema: fieldValidationSchema
            };
        case 'matches':
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
        case 'teams':
            return teamFields;
        case 'fields':
            return fieldFields;
        case 'matches':
            return matchFields;
        default:
            throw new Error(`Unknown form type for fields: ${type}`);
    }
}
