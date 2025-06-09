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
        name: 'ownerId',
        type: 'number',
        id: 'field-ownerId',
        htmlFor: 'field-ownerId',
        placeholder: 'Owner ID',
        label: 'Owner ID'
    },
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
        placeholder: 'Ubicación',
        label: 'Ubicación de la cancha'
    },
    {
        name: 'zone',
        type: 'text',
        id: 'field-zone',
        htmlFor: 'field-zone',
        placeholder: 'Zona',
        label: 'Zona de la cancha'
    },
    {
        name: 'features',
        type: 'text',
        id: 'field-features',
        htmlFor: 'field-features',
        placeholder: 'Características (separadas por comas)',
        label: 'Características de la cancha',
        isList: true
    },
    {
        name: 'images',
        type: 'text',
        id: 'field-images',
        htmlFor: 'field-images',
        placeholder: 'Imágenes (separadas por comas)',
        label: 'Imágenes de la cancha',
        isList: true
    }
];

export const matchFields = [
    {
        name: 'fieldId',
        type: 'number',
        id: 'match-fieldId',
        htmlFor: 'match-fieldId',
        placeholder: 'ID',
        label: 'ID de la cancha'
    },
    {
        name: 'participationType.type',
        type: 'select',
        id: 'match-participationType-type',
        htmlFor: 'match-participationType-type',
        placeholder: 'Tipo de partido',
        label: 'Tipo de partido',
        options: [
            { value: 'Open', label: 'Open' },
            { value: 'Close', label: 'Close' }
        ]
    },
    {
        name: 'participationType.minPlayersCount',
        type: 'number',
        id: 'match-minPlayersCount',
        htmlFor: 'match-minPlayersCount',
        placeholder: 'Cantidad Mínima de Jugadores (sólo para partidos abiertos)',
        label: 'Mínimo'
    },
    {
        name: 'participationType.maxPlayersCount',
        type: 'number',
        id: 'match-maxPlayersCount',
        htmlFor: 'match-maxPlayersCount',
        placeholder: 'Cantidad Máxima de Jugadores (sólo para partidos abiertos)',
        label: 'Máximo'
    },
    {
        name: 'participationType.players',
        type: 'text',
        id: 'match-players',
        htmlFor: 'match-players',
        placeholder: 'Jugadores (separados por comas)',
        label: 'Jugadores (sólo para partidos cerrados)',
    },
    {
        name: 'timeRange.start',
        type: 'time',
        id: 'match-timeRange-start',
        htmlFor: 'match-timeRange-start',
        placeholder: 'Hora de Inicio',
        label: 'Inicio'
    },
    {
        name: 'timeRange.end',
        type: 'time',
        id: 'match-timeRange-end',
        htmlFor: 'match-timeRange-end',
        placeholder: 'Hora de Finalización',
        label: 'Fin'
    }
];