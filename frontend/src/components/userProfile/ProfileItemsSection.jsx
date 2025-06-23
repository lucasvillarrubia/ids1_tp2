import React from 'react'
import ProfileItemCard from './ProfileItemCard'
import { ProfileItemCardsContainer, ProfileSectionTitle } from './ProfileStyles'
import { useSelector } from 'react-redux'

const ProfileItemsSection = ({ category }) => {
	const items = useSelector(state => {
		switch (category) {
			case 'matches':
				return state.matches.list.content;
			case 'fields':
				return state.fields.list
			case 'teams':
				return Object.values(state.teams.list)
			default:
				return []
		}
	})

	let titleCat
	switch (category) {
		case 'matches':
			titleCat = 'Partidos'
			break
		case 'teams':
			titleCat = 'Equipos'
			break
		case 'fields':
			titleCat = 'Canchas'
			break
		default:
			titleCat = 'Ítems'
	}

	function renderProfileItemsSection() {
		if (!items || items.length === 0) {
			return <h6>No creaste nada todavía</h6>
		}
		return Array.isArray(items) && items.map((item, i) => (
			<ProfileItemCard key={item._id || item.name || i} {...item} category={category} />
		))
	}

	return (
		<>
			<ProfileSectionTitle>MIS {titleCat}</ProfileSectionTitle>
			<ProfileItemCardsContainer>
				{renderProfileItemsSection()}
			</ProfileItemCardsContainer>
		</>
	)
}

export default ProfileItemsSection
