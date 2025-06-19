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

const MyProfile = () => {
	const navigate = useNavigate();
	const dispatch = useDispatch();

	useEffect(() => {
		dispatch(fetchTeams())
		dispatch(fetchFields())
		dispatch(fetchMatches())
	}, [dispatch])

	return (
		<MyProfileUI>
			<UserProfilePage />
			{/*<MyProfileTitle>Perfíl</MyProfileTitle>*/}
			{/* <UserInfo />*/}
			<ProfileItemsSection category={"matches"} />
			<ProfileItemsSection category={"teams"} />
			<ProfileItemsSection category={"fields"} />
			<MyProfileButton onClick={() => navigate('/')}>Volver a inicio</MyProfileButton>
		</MyProfileUI>
	)
}

export default MyProfile