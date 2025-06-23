import React from 'react'
import {
    ProfileItemCardUI,
    ProfileItemCardNumber,
    ProfileItemCardTotal,
    ProfileItemCardDate
} from './ProfileStyles'
import { useNavigate } from 'react-router-dom'

const ProfileItemCard = (item) => {
    const navigate = useNavigate()

    let label = ''
    let extraInfo = ''
    const keyOrId = item.id ?? item._id ?? item.name
    const encodedKey = encodeURIComponent(keyOrId)
    switch (item.category) {
        case 'teams':
            label = `Equipo: ${item.name}`
            extraInfo = `Capitán: ${item.captain}`
            break

        case 'fields':
            label = `Cancha: ${item.name}`
            extraInfo = `Ubicación: ${item.location}`
            break

        case 'matches':
            label = `Partido #${item.id}`
            extraInfo = `Tipo: ${item.participationType.type}`
            break

        default:
            label = 'Ítem'
    }

    return (
        <ProfileItemCardUI onClick={() => navigate(`/${item.category}/${encodedKey}`)}>
            <ProfileItemCardNumber>{label}</ProfileItemCardNumber>
            {extraInfo && <ProfileItemCardDate>{extraInfo}</ProfileItemCardDate>}
        </ProfileItemCardUI>
    )
}

export default ProfileItemCard
