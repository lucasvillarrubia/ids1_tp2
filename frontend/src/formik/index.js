import {
    fieldInitialValues, fieldUpdateInitialValues,
    matchClosedInitialValues,
    matchOpenInitialValues,
    teamInitialValues
} from "./initialValues.js";
import {
    fieldUpdateValidationSchema,
    fieldValidationSchema,
    matchClosedValidationSchema,
    matchOpenValidationSchema,
    teamValidationSchema
} from "./validationSchema.js";
import {fieldFields, fieldUpdateFields, matchClosedFields, matchOpenFields, teamFields} from "./itemsFields.js";

export const getFormConfig = (type, existingItem = null) => {
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
                initialValues: matchOpenInitialValues,
                validationSchema: matchOpenValidationSchema
            };
        case 'closed':
            return {
                initialValues: matchClosedInitialValues,
                validationSchema: matchClosedValidationSchema
            };
        case 'updateField':
            return {
                initialValues: fieldUpdateInitialValues(existingItem),
                validationSchema: fieldUpdateValidationSchema
            };
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
            return matchOpenFields;
        case 'closed':
            return matchClosedFields;
        case 'updateField':
            return fieldUpdateFields;
        default:
            throw new Error(`Unknown form type for fields: ${type}`);
    }
}
