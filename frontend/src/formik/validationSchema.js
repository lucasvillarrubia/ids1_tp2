import * as Yup from 'yup'
import { REG_EMAIL } from '../utils/constants';


export const signupValidationSchema = Yup.object({
    name: Yup.string().required('Campo obligatorio'),
    lastname: Yup.string().required('Campo obligatorio'),
    email: Yup.string()
        .matches(REG_EMAIL, 'Correo inválido')
        .required('Campo obligatorio'),
    password: Yup.string()
        .min(0, 'La contraseña debe tener por lo menos 8 caracteres')
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
    zones: Yup.array()
        .of(Yup.string())
        .min(1, 'Seleccioná al menos una zona')
        .required('Campo obligatorio')
});

export const loginValidationSchema = Yup.object({
        email: Yup.string().matches(REG_EMAIL, 'Correo inválido').required('Campo obligatorio'),
        password: Yup.string().min(0, 'La contraseña debe tener por lo menos 8 caracteres').required('Campo obligatorio')
});

export const verifyValidationSchema = Yup.object({
        email: Yup.string().matches(REG_EMAIL, 'Correo inválido').required('Campo obligatorio'),
        code: Yup.string().min(6, 'El código sólo tiene 6 caracteres').max(6, 'El código sólo tiene 6 caracteres').required('Campo obligatorio')
});

export const teamValidationSchema = Yup.object({
    name: Yup.string().required('Nombre obligatorio'),
    // logo: Yup.string().nullable(),
    colors: Yup.string().required('Colores obligatorios'),
    skill: Yup.number()
        .min(1, 'Mínimo 1')
        .max(10, 'Máximo 10')
        .nullable(),
    // players: Yup.string()
    //     .test('max-players', 'Máximo 4 jugadores', value => {
    //         if (!value) return true;
    //         const players = value.split(',').map(p => p.trim()).filter(Boolean);
    //         return players.length <= 4;
    //     })
    //     .nullable(),
    players: Yup.array()
        .of(
            Yup.string()
                .trim()
                .required('El nombre no puede estar vacío')
        )
        // .min(1, 'Debe haber al menos un jugador')
        .max(4, 'Máximo 4 jugadores')
        .test('sin-duplicados', 'No puede haber jugadores repetidos', (players) => {
            if (!Array.isArray(players)) return true;
            const clean = players.filter(p => typeof p === 'string' && p.trim() !== '');
            const lowercased = clean.map(p => p.toLowerCase());
            const set = new Set(lowercased);
            return set.size === lowercased.length;
        })
});

export const fieldValidationSchema = Yup.object({
    name: Yup.string().required('Nombre requerido'),
    location: Yup.string().required('Ubicación requerida'),
    zone: Yup.string().required('Zona requerida'),
    features: Yup.array().min(1, 'Debes seleccionar al menos una característica'),
    images: Yup.string().nullable()
});

export const matchOpenValidationSchema = Yup.object({
    participationType: Yup.object({
        type: Yup.string().oneOf(['Open']).required(),
        minPlayersCount: Yup.number()
            .required('Mínimo requerido')
            .min(2, 'Al menos 2 jugadores'),
        maxPlayersCount: Yup.number()
            .required('Máximo requerido')
            .moreThan(Yup.ref('minPlayersCount'), 'Debe ser mayor al mínimo')
        // , players: Yup.array()
        //     .of(
        //         Yup.string()
        //             .trim()
        //             .required('El nombre no puede estar vacío')
        //     )
        //     // .min(1, 'Debe haber al menos un jugador')
        //     .test('sin-duplicados', 'No puede haber jugadores repetidos', (players) => {
        //         if (!Array.isArray(players)) return true;
        //         const clean = players.filter(p => typeof p === 'string' && p.trim() !== '');
        //         const lowercased = clean.map(p => p.toLowerCase());
        //         const set = new Set(lowercased);
        //         return set.size === lowercased.length;
        //     })
    }),
    reservation: Yup.object({
        fieldId: Yup.number().required('ID requerido'),
        date: Yup.string()
            .required('Fecha requerida')
            .matches(/^\d{4}-\d{2}-\d{2}$/, 'Formato inválido'),
        start: Yup.string()
            .required('Inicio requerido')
            .matches(/^\d{2}:\d{2}$/, 'Formato inválido'),
        end: Yup.string()
            .required('Fin requerido')
            .matches(/^\d{2}:\d{2}$/, 'Formato inválido'),
    })
});

export const matchClosedValidationSchema = Yup.object({
    participationType: Yup.object({
        type: Yup.string()
            .oneOf(['Close'])
            .required(),
        teama: Yup.string().required('Equipo A requerido'),
        teamb: Yup.string().required('Equipo B requerido')

    }),

    reservation: Yup.object({
        fieldId: Yup.number()
            .typeError('Debe ser un número')
            .required('ID requerido'),

        date: Yup.string()
            .required('Fecha requerida')
            .matches(/^\d{4}-\d{2}-\d{2}$/, 'Formato inválido'),

        start: Yup.string()
            .required('Hora de inicio requerida')
            .matches(/^\d{2}:\d{2}$/, 'Formato inválido'),

        end: Yup.string()
            .required('Hora de fin requerida')
            .matches(/^\d{2}:\d{2}$/, 'Formato inválido'),
    })
});

export const fieldUpdateValidationSchema = Yup.object({
    name: Yup.string().required("Nombre requerido"),
    description: Yup.string(),
    location: Yup.string().required("Ubicación requerida"),
    zone: Yup.string().required("Zona requerida"),
    price: Yup.number().min(0).required("Precio requerido"),
    features: Yup.array().min(1, 'Debes seleccionar al menos una característica'),
    images: Yup.array()
        .of(
            Yup.string()
                .trim()
                .required('La imágen no puede estar vacía')
        )
        // .min(1, 'Debe haber al menos un jugador')
        .test('sin-duplicados', 'No puede haber imágenes repetidas', (images) => {
            if (!Array.isArray(images)) return true;
            const clean = images.filter(p => typeof p === 'string' && p.trim() !== '');
            const lowercased = clean.map(p => p.toLowerCase());
            const set = new Set(lowercased);
            return set.size === lowercased.length;
        }),
    schedule: Yup.object({
        days: Yup.string().required('Días requeridos'),
        startHour: Yup.string().required("Hora inicio requerida"),
        endHour: Yup.string().required("Hora fin requerida"),
        predefDuration: Yup.number().required()
    })
});
