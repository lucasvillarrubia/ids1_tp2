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

export const teamValidationSchema = Yup.object({
    name: Yup.string().required('Nombre obligatorio'),
    logo: Yup.string().nullable(), // if this were a file, use Yup.mixed()
    colors: Yup.string().required('Colores obligatorios'),
    skill: Yup.number()
        .min(1, 'Mínimo 1')
        .max(10, 'Máximo 10')
        .nullable(),
    players: Yup.string()
        .test('max-players', 'Máximo 4 jugadores', value => {
            if (!value) return true;
            const players = value.split(',').map(p => p.trim()).filter(Boolean);
            return players.length <= 4;
        })
        .nullable(),
});

export const fieldValidationSchema = Yup.object({
    ownerId: Yup.number().typeError('Debe ser un número').required('Owner ID requerido'),
    name: Yup.string().required('Nombre requerido'),
    location: Yup.string().required('Ubicación requerida'),
    zone: Yup.string().required('Zona requerida'),
    features: Yup.string().nullable(), // comma-separated string
    images: Yup.string().nullable(),   // comma-separated string or list of URLs
});

export const matchValidationSchema = Yup.object({
    fieldId: Yup.number().typeError('Debe ser un número').required('Field ID requerido'),

    participationType: Yup.object({
        type: Yup.string().oneOf(['Open', 'Close']).required('Tipo requerido'),
        minPlayersCount: Yup.number()
            .typeError('Debe ser un número')
            .when('type', {
                is: 'Open',
                then: schema => schema.required('Requerido en modo Open').min(1),
                otherwise: schema => schema.notRequired(),
            }),
        maxPlayersCount: Yup.number()
            .typeError('Debe ser un número')
            .when('type', {
                is: 'Open',
                then: schema => schema.required('Requerido en modo Open').max(50),
                otherwise: schema => schema.notRequired(),
            }),
        players: Yup.string()
            .when('type', {
                is: 'Open',
                then: schema =>
                    schema.test('max-players', 'Máximo 50 jugadores', value => {
                        if (!value) return true;
                        const players = value.split(',').map(p => p.trim()).filter(Boolean);
                        return players.length <= 50;
                    }),
                otherwise: schema => schema.notRequired(),
            }),
    }),

    timeRange: Yup.object({
        start: Yup.string().required('Hora de inicio requerida'),
        end: Yup.string().required('Hora de fin requerida'),
    }),
});
