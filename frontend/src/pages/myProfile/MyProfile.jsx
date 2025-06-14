import React, { useEffect } from 'react'
import UserInfo from '../../components/userInfo/UserInfo'
import { useDispatch, useSelector } from 'react-redux'
import { useNavigate } from 'react-router-dom'
import ProfileItemsSection from "../../components/userProfile/ProfileItemsSection.jsx";
import {MyProfileButton, MyProfileTitle, MyProfileUI} from "./MyProfileStyles.js";

const MyProfile = () => {
	const navigate = useNavigate();
	return (
		<MyProfileUI>
			<MyProfileTitle>Perfíl</MyProfileTitle>
			 <UserInfo />
			<ProfileItemsSection category={"matches"} />
			<ProfileItemsSection category={"teams"} />
			<ProfileItemsSection category={"fields"} />
			<MyProfileButton onClick={() => navigate('/')}>Volver a inicio</MyProfileButton>
		</MyProfileUI>
	)
}

export default MyProfile