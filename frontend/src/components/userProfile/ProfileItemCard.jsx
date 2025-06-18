import React from 'react'
import { ProfileItemCardUI, ProfileItemCardNumber, ProfileItemCardTotal, ProfileItemCardDate } from './ProfileStyles'
import { formatPrice } from '../../utils/formatPrice'
import { formatDate } from '../../utils/formatDate'
import { useNavigate } from 'react-router-dom'

const ProfileItemCard = (item) => {
        const navigate = useNavigate();

        return (
                <ProfileItemCardUI onClick={() => navigate(`/my-${item.category}/${item._id}`)}>
                        <ProfileItemCardNumber>Ítem #{item._id}</ProfileItemCardNumber>
                        <ProfileItemCardDate>Creado el {formatDate(item.createdAt)}</ProfileItemCardDate>
                        <ProfileItemCardTotal>{formatPrice(item.total)}</ProfileItemCardTotal>
                </ProfileItemCardUI>
        )
}

export default ProfileItemCard