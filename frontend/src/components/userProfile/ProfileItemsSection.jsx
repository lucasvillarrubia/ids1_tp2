import React from 'react'
import ProfileItemCard from './ProfileItemCard'
import {ProfileItemCardsContainer, ProfileSectionTitle} from './ProfileStyles'
import { useSelector } from 'react-redux'

const ProfileItemsSection = ({category}) => {

	function renderProfileItemsSection () {
		// const itemsArray = Object.entries(itemsObj).map(([ _itemId, item ]) => { return item });
		const itemsArray = [
				{
					_id: "item1",
					createdAt: "2025-06-01T12:34:56Z",
					total: 1999.99,
				},
				{
					_id: "item2",
					createdAt: "2025-05-28T08:00:00Z",
					total: 349.5,
				},
				{
					_id: "item3",
					createdAt: "2025-05-20T16:20:00Z",
					total: 780.0,
				},
		];
		let i = 0;
		if (!itemsArray.length) { return (<h6>No creaste nada todavía</h6>) }
		return itemsArray.map(item => {
			i++; 
			return (<ProfileItemCard key={i} {...item} />);
		});
	}

	let titleCat;
	switch (category) {
		case "matches":
			titleCat = "Partidos";
			break;
		case "teams":
			titleCat = "Equipos";
			break;
		case "fields":
			titleCat = "Canchas";
			break;
		default:
			titleCat = "Ítems";
	}

	return (
		<>
			<ProfileSectionTitle> MIS {titleCat}</ProfileSectionTitle>
			<ProfileItemCardsContainer>
				{
					renderProfileItemsSection()
				}
			</ProfileItemCardsContainer>
		</>
	)
}

export default ProfileItemsSection