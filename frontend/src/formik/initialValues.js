
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


