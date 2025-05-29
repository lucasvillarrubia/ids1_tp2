import * as Yup from 'yup'
import { REG_EMAIL } from '../utils/constants';


export const signupValidationSchema = Yup.object({
    name: Yup.string().required('Campo obligatorio'),
    lastname: Yup.string().required('Campo obligatorio'),
    email: Yup.string()
        .matches(REG_EMAIL, 'Correo inválido')
        .required('Campo obligatorio'),
    password: Yup.string()
        .min(8, 'La contraseña debe tener por lo menos 8 caracteres')
        .required('Campo obligatorio'),
    age: Yup.number()
        .min(0, 'Edad no válida')
        .max(120, 'Edad no válida')
        .required('Campo obligatorio'),
    gender: Yup.string()
        .oneOf(['male', 'female', 'other'], 'Género inválido')
        .required('Campo obligatorio'),
    photo: Yup.string().url('URL inválida').nullable(),
    role: Yup.string().nullable(),
    zone: Yup.string().required('Campo obligatorio'),
});

export const loginValidationSchema = Yup.object({
        email: Yup.string().matches(REG_EMAIL, 'Correo inválido').required('Campo obligatorio'),
        password: Yup.string().min(8, 'La contraseña debe tener por lo menos 8 caracteres').required('Campo obligatorio')
});

export const verifyValidationSchema = Yup.object({
        email: Yup.string().matches(REG_EMAIL, 'Correo inválido').required('Campo obligatorio'),
        code: Yup.string().min(6, 'El código sólo tiene 6 caracteres').max(6, 'El código sólo tiene 6 caracteres').required('Campo obligatorio')
});