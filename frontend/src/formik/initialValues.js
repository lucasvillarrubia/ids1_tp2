
export const checkoutInitialValues = {
        name: '',
        phone: '',
        location: '',
        address: ''
}

export const signupInitialValues = {
    name: '',
    lastname: '',
    email: '',
    password: '',
    age: '',
    gender: 'other',
    zone: '',
};

export const loginInitialValues = {
        email: '',
        password: ''
}

export const verifyInitialValues = {
        email: '',
        code: ''
}

export const teamInitialValues = {
    name: '',
    logo: '',
    colors: '',
    skill: '',
    players: ['']
};

export const fieldInitialValues = {
    name: '',
    location: '',
    zone: '',
    features: [],
};

// export const matchInitialValues = {
//     fieldId: 1,
//     participationType: {
//         type: 'Open',
//         minPlayersCount: 2,
//         maxPlayersCount: 10,
//         players: []
//     },
//     timeRange: {
//         start: '19:00:00',
//         end: '20:00:00'
//     },
// };

export const matchOpenInitialValues = {
    participationType: {
        type: 'Open',
        minPlayersCount: 5,
        maxPlayersCount: 10,
        players: ['']
    },
    reservation: {
        fieldId: 1,
        date: '2025-06-17',
        start: '18:00',
        end: '19:00'
    }
};

export const matchClosedInitialValues = {
    participationType: {
        type: 'Close',
        teama: '',
        teamb: ''
        // minPlayersCount: 2,
        // maxPlayersCount: 22,
        // players: ['']
    },
    reservation: {
        fieldId: 1,
        date: '2025-06-17',
        start: '18:00',
        end: '19:00'
    }
};

export const fieldUpdateInitialValues = (field) => ({
    name: field?.name || '',
    description: field?.description || '',
    location: field?.location || '',
    zone: field?.zone || '',
    price: field?.price || 0,
    features: [],
    images: field?.images || [''],
    schedule: {
        days: field?.schedule?.days || '',
        startHour: field?.schedule?.startHour || '',
        endHour: field?.schedule?.endHour || '',
        predefDuration: field?.schedule?.predefDuration || 60
    }
});

export const matchUpdateInitialValues = (match) => ({
    participationType: {
        type: 'Open',
        minPlayersCount: match?.participationType?.minPlayersCount || 0,
        maxPlayersCount: match?.participationType?.maxPlayersCount || 0,
        players: match?.participationType?.players || [''],
    },
    reservation: {
        fieldId: match?.reservation?.fieldId || 0,
        date: match?.reservation?.date || '',
        start: match?.reservation?.start || '',
        end: match?.reservation?.end || '',
    }
});

export const teamUpdateInitialValues = (team) => ({
    name: team?.name || '',
    logo: team?.logo || '',
    colors: team?.colors || '',
    skill: team?.skill || '',
    players: team?.players || ['']
});




