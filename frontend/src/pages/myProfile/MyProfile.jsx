import React, { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { useNavigate } from 'react-router-dom'
import ProfileItemsSection from "../../components/userProfile/ProfileItemsSection.jsx";
import {MyProfileButton, MyProfileTitle, MyProfileUI} from "./MyProfileStyles.js";
import UserProfilePage from "../../components/userProfilePage/UserProfilePage.jsx";
import {getMyFields} from "../../features/fields/fieldsAPI.js";
import {getMyMatches} from "../../features/matches/matchesAPI.js";
import {getMyTeams} from "../../features/teams/teamsAPI.js";

const MyProfile = () => {
	const navigate = useNavigate();
	const dispatch = useDispatch();
	const { currentUser } = useSelector(state => state.users);

	useEffect(() => {
		dispatch(getMyTeams(currentUser.email));
		dispatch(getMyFields(currentUser.email));
		dispatch(getMyMatches());
	}, [dispatch, navigate]);

	return (
		<MyProfileUI>
			<MyProfileTitle>Mí Perfíl</MyProfileTitle>
			<UserProfilePage />
			<ProfileItemsSection category={"matches"} />
			<ProfileItemsSection category={"teams"} />
			<ProfileItemsSection category={"fields"} />
			<MyProfileButton onClick={() => {
				navigate('/create');
			}}>CREAR</MyProfileButton>
			<MyProfileButton onClick={() => navigate('/')}>Volver a inicio</MyProfileButton>
		</MyProfileUI>
	)
}

export default MyProfile