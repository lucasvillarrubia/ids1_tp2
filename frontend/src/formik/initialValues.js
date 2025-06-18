
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
    players: '',
};

export const fieldInitialValues = {
    name: '',
    location: '',
    zone: '',
    features: [],
};

export const matchInitialValues = {
    fieldId: 1,
    participationType: {
        type: 'Open',
        minPlayersCount: 2,
        maxPlayersCount: 10,
        players: []
    },
    timeRange: {
        start: '19:00:00',
        end: '20:00:00'
    },
};
