export const teamFields = [
    {
        name: 'name',
        type: 'text',
        id: 'team-name',
        htmlFor: 'team-name',
        placeholder: 'Nombre',
        label: 'Nombre del equipo'
    },
    {
        name: 'logo',
        type: 'text',
        id: 'team-logo',
        htmlFor: 'team-logo',
        placeholder: 'Logo',
        label: 'Logo del equipo',
    },
    {
        name: 'colors',
        type: 'text',
        id: 'team-colors',
        htmlFor: 'team-colors',
        placeholder: 'Colores',
        label: 'Colores del equipo (separados por comas)'
    },
    {
        name: 'skill',
        type: 'number',
        id: 'team-skill',
        htmlFor: 'team-skill',
        placeholder: 'Nivel de habilidad (1-10)',
        label: 'Nivel de habilidad'
    },
    {
        name: 'players',
        type: 'text',
        id: 'team-players',
        htmlFor: 'team-players',
        placeholder: 'Jugadores (separados por comas)',
        label: 'Jugadores'
    }
];
// htmlFor, type, id, placeholder, name

export const fieldFields = [
    {
        name: 'name',
        type: 'text',
        id: 'field-name',
        htmlFor: 'field-name',
        placeholder: 'Nombre',
        label: 'Nombre de la cancha'
    },
    {
        name: 'location',
        type: 'text',
        id: 'field-location',
        htmlFor: 'field-location',
        placeholder: 'Dirección',
        label: 'Dirección de la cancha'
    },
    {
        name: 'zone',
        type: 'select',
        id: 'field-zone',
        htmlFor: 'field-zone',
        placeholder: 'Zona',
        label: 'Zona de la cancha',
        options: [
            { value: 'VICENTE_LOPEZ', label: 'Vicente Lopez' },
            { value: 'SAN_ISIDRO', label: 'San Isidro' },
            { value: 'SAN_FERNANDO', label: 'San Fernando' },
            { value: 'TIGRE', label: 'Tigre' },
            { value: 'ESCOBAR', label: 'Escobar' },
            { value: 'PILAR', label: 'Pilar' },
            { value: 'JOSE_C_PAZ', label: 'Jose C Paz' },
            { value: 'MALVINAS_ARGENTINAS', label: 'Malvinas Argentinas' },
            { value: 'SAN_MIGUEL', label: 'San Miguel' },
            { value: 'SAN_MARTIN', label: 'San Martin' },
            { value: 'LA_MATANZA', label: 'La Matanza' },
            { value: 'TRES_DE_FEBRERO', label: 'Tres De Febrero' },
            { value: 'HURLINGHAM', label: 'Hurlingham' },
            { value: 'MORON', label: 'Moron' },
            { value: 'ITUZAINGO', label: 'Ituzaingo' },
            { value: 'MERLO', label: 'Merlo' },
            { value: 'MORENO', label: 'Moreno' },
            { value: 'GENERAL_RODRIGUEZ', label: 'General Rodriguez' },
            { value: 'MARCOS_PAZ', label: 'Marcos Paz' },
            { value: 'AVELLANEDA', label: 'Avellaneda' },
            { value: 'LANUS', label: 'Lanus' },
            { value: 'LOMAS_DE_ZAMORA', label: 'Lomas De Zamora' },
            { value: 'ESTEBAN_DE_ECHEVERRIA', label: 'Esteban De Echeverria' },
            { value: 'EZEIZA', label: 'Ezeiza' },
            { value: 'SAN_VICENTE', label: 'San Vicente' },
            { value: 'PRESIDENTE_PERON', label: 'Presidente Peron' },
            { value: 'ALMIRANTE_BROWN', label: 'Almirante Brown' },
            { value: 'QUILMES', label: 'Quilmes' },
            { value: 'BERAZATEGUI', label: 'Berazategui' },
            { value: 'FLORENCIO_VARELA', label: 'Florencio Varela' },
            { value: 'AGRONOMIA', label: 'Agronomia' },
            { value: 'ALMAGRO', label: 'Almagro' },
            { value: 'BALVANERA', label: 'Balvanera' },
            { value: 'BARRACAS', label: 'Barracas' },
            { value: 'BELGRANO', label: 'Belgrano' },
            { value: 'BOEDO', label: 'Boedo' },
            { value: 'CABALLITO', label: 'Caballito' },
            { value: 'CHACARITA', label: 'Chacarita' },
            { value: 'COGHLAN', label: 'Coghlan' },
            { value: 'COLEGIALES', label: 'Colegiales' },
            { value: 'CONSTITUCION', label: 'Constitucion' },
            { value: 'FLORES', label: 'Flores' },
            { value: 'FLORESTA', label: 'Floresta' },
            { value: 'LA_BOCA', label: 'La Boca' },
            { value: 'LA_PATERNAL', label: 'La Paternal' },
            { value: 'LINIERS', label: 'Liniers' },
            { value: 'MATADEROS', label: 'Mataderos' },
            { value: 'MONTE_CASTRO', label: 'Monte Castro' },
            { value: 'MONSERRAT', label: 'Monserrat' },
            { value: 'NUEVA_POMPEYA', label: 'Nueva Pompeya' },
            { value: 'NUNEZ', label: 'Nunez' },
            { value: 'PALERMO', label: 'Palermo' },
            { value: 'PARQUE_AVELLANEDA', label: 'Parque Avellaneda' },
            { value: 'PARQUE_CHACABUCO', label: 'Parque Chacabuco' },
            { value: 'PARQUE_CHAS', label: 'Parque Chas' },
            { value: 'PARQUE_PATRICIOS', label: 'Parque Patricios' },
            { value: 'PUERTO_MADERO', label: 'Puerto Madero' },
            { value: 'RECOLETA', label: 'Recoleta' },
            { value: 'RETIRO', label: 'Retiro' },
            { value: 'SAAVEDRA', label: 'Saavedra' },
            { value: 'SAN_CRISTOBAL', label: 'San Cristobal' },
            { value: 'SAN_NICOLAS', label: 'San Nicolas' },
            { value: 'SAN_TELMO', label: 'San Telmo' },
            { value: 'VELEZ_SARSFIELD', label: 'Velez Sarsfield' },
            { value: 'VERSALLES', label: 'Versalles' },
            { value: 'VILLA_CRESPO', label: 'Villa Crespo' },
            { value: 'VILLA_DEL_PARQUE', label: 'Villa Del Parque' },
            { value: 'VILLA_DEVOTO', label: 'Villa Devoto' },
            { value: 'VILLA_GENERAL_MITRE', label: 'Villa General Mitre' }
        ]
    },
    {
        name: 'features',
        type: 'checkbox-group',
        id: 'field-features',
        htmlFor: 'field-features',
        placeholder: 'Características',
        label: 'Características de la cancha',
        options: [
            { value: 'GRASS', label: 'Césped' },
            { value: 'CEMENT', label: 'Cemento' },
            { value: 'ARTIFICIAL_TURF', label: 'Césped sintético' },
            { value: 'ROOF', label: 'Techo' },
            { value: 'LIGHTS', label: 'Luces' },
            { value: 'RESTROOMS', label: 'Baños' },
            { value: 'PARKING', label: 'Estacionamiento' },
            { value: 'WIFI', label: 'WiFi' },
            { value: 'FOOD', label: 'Comida' },
            { value: 'DRINK', label: 'Bebidas' },
            { value: 'MUSIC', label: 'Música' }
        ]
    }
];

// export const matchFields = [
//     {
//         name: 'fieldId',
//         type: 'number',
//         id: 'match-fieldId',
//         htmlFor: 'match-fieldId',
//         placeholder: 'ID',
//         label: 'ID de la cancha',
//         min: '0'
//     },
//     {
//         name: 'participationType.type',
//         type: 'select',
//         id: 'match-participationType-type',
//         htmlFor: 'match-participationType-type',
//         placeholder: 'Tipo de partido',
//         label: 'Tipo de partido',
//         options: [
//             { value: 'Open', label: 'Open' },
//             //{ value: 'Close', label: 'Close' }
//         ]
//     },
//     {
//         name: 'participationType.minPlayersCount',
//         type: 'number',
//         id: 'match-minPlayersCount',
//         htmlFor: 'match-minPlayersCount',
//         placeholder: 'Cantidad Mínima de Jugadores (sólo para partidos abiertos)',
//         label: 'Mínimo',
//         min: '2',
//         max: '10',
//         step: '2'
//     },
//     {
//         name: 'participationType.maxPlayersCount',
//         type: 'number',
//         id: 'match-maxPlayersCount',
//         htmlFor: 'match-maxPlayersCount',
//         placeholder: 'Cantidad Máxima de Jugadores (sólo para partidos abiertos)',
//         label: 'Máximo',
//         min: '2',
//         max: '10',
//         step: '2'
//     },
//     {
//         name: 'timeRange.start',
//         type: 'time',
//         id: 'match-timeRange-start',
//         htmlFor: 'match-timeRange-start',
//         placeholder: 'Hora de Inicio',
//         step: '1',
//         label: 'Inicio'
//     },
//     {
//         name: 'timeRange.end',
//         type: 'time',
//         id: 'match-timeRange-end',
//         htmlFor: 'match-timeRange-end',
//         placeholder: 'Hora de Finalización',
//         step: '1',
//         label: 'Fin'
//     }
// ];

export const matchOpenFields = [
    {
        name: 'reservation.fieldId',
        type: 'number',
        id: 'match-reservation-fieldId',
        htmlFor: 'match-reservation-fieldId',
        placeholder: 'ID',
        label: 'ID de la cancha',
        min: '0'
    },
    {
        name: 'reservation.date',
        type: 'date',
        id: 'match-reservation-date',
        htmlFor: 'match-reservation-date',
        label: 'Fecha'
    },
    {
        name: 'reservation.start',
        type: 'time',
        id: 'match-reservation-start',
        htmlFor: 'match-reservation-start',
        label: 'Inicio'
    },
    {
        name: 'reservation.end',
        type: 'time',
        id: 'match-reservation-end',
        htmlFor: 'match-reservation-end',
        label: 'Fin'
    },
    {
        name: 'participationType.minPlayersCount',
        type: 'number',
        id: 'match-minPlayersCount',
        htmlFor: 'match-minPlayersCount',
        label: 'Mínimo de jugadores',
        min: '2',
        max: '50',
        step: '1'
    },
    {
        name: 'participationType.maxPlayersCount',
        type: 'number',
        id: 'match-maxPlayersCount',
        htmlFor: 'match-maxPlayersCount',
        label: 'Máximo de jugadores',
        min: '2',
        max: '50',
        step: '1'
    },
    {
        name: 'participationType.players',
        type: 'text',
        id: 'match-closed-players',
        htmlFor: 'match-closed-players',
        placeholder: 'Nombre del jugador',
        label: 'Jugadores',
        isList: true
    }
];

export const matchClosedFields = [
    {
        name: 'reservation.fieldId',
        type: 'number',
        id: 'match-reservation-fieldId',
        htmlFor: 'match-reservation-fieldId',
        placeholder: 'ID',
        label: 'ID de la cancha',
        min: '0'
    },
    {
        name: 'reservation.date',
        type: 'date',
        id: 'match-reservation-date',
        htmlFor: 'match-reservation-date',
        label: 'Fecha'
    },
    {
        name: 'reservation.start',
        type: 'time',
        id: 'match-reservation-start',
        htmlFor: 'match-reservation-start',
        label: 'Inicio'
    },
    {
        name: 'reservation.end',
        type: 'time',
        id: 'match-reservation-end',
        htmlFor: 'match-reservation-end',
        label: 'Fin'
    },
    {
        name: 'participationType.players',
        type: 'text',
        id: 'match-closed-players',
        htmlFor: 'match-closed-players',
        placeholder: 'Nombre del jugador',
        label: 'Jugadores',
        isList: true
    }
];
