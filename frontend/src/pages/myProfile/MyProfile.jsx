import React, { useEffect } from 'react'
import UserInfo from '../../components/userInfo/UserInfo'
import { useDispatch, useSelector } from 'react-redux'
import { useNavigate } from 'react-router-dom'
import ProfileItemsSection from "../../components/userProfile/ProfileItemsSection.jsx";
import {MyProfileButton, MyProfileTitle, MyProfileUI} from "./MyProfileStyles.js";
import UserProfilePage from "../../components/userProfilePage/UserProfilePage.jsx";
import {fetchMatches} from "../../features/matches/matchesSlice.js";
import {fetchFields} from "../../features/fields/fieldsSlice.js";
import {fetchTeams} from "../../features/teams/teamsSlice.js";
import {getMyFields} from "../../features/fields/fieldsAPI.js";
import {getMyMatches} from "../../features/matches/matchesAPI.js";
import {getMyTeams} from "../../features/teams/teamsAPI.js";
import AddInstanceButton from "../../components/items/AddInstanceButton.jsx";

const MyProfile = () => {
	const navigate = useNavigate();
	const dispatch = useDispatch();
	const { currentUser } = useSelector(state => state.users);

	// useEffect(() => {
		dispatch(getMyTeams(currentUser.email))
		// dispatch(fetchTeams());
		dispatch(getMyFields(currentUser.email));
		// dispatch(fetchFields())
		dispatch(getMyMatches());
		// dispatch(fetchMatches())
	// }, [dispatch]);

	return (
		<MyProfileUI>
			<MyProfileTitle>Mí Perfíl</MyProfileTitle>
			<UserProfilePage />
			<ProfileItemsSection category={"matches"} />
			<ProfileItemsSection category={"teams"} />
			<ProfileItemsSection category={"fields"} />
			<MyProfileButton onClick={() => {
				// console.log('Navigating to create page');
				// console.log('Selected Category:', selectedCat);
				navigate('/create');
			}}>CREAR</MyProfileButton>
			<MyProfileButton onClick={() => navigate('/')}>Volver a inicio</MyProfileButton>
		</MyProfileUI>
	)
}

export default MyProfile