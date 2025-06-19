import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import {getUserProfile } from '../../features/users/usersAPI'

import {ProfileContainer, ProfileSection, ProfileTitle,ProfileSubtitle,ProfileInputField,
  ProfileDetailRow, PrimaryButton,SecondaryButton,ErrorMessageUI,SectionSeparator} from './userProfileStyles';

const UserProfilePage = () => {
  const navigate = useNavigate();
  const {currentUser,token} = useSelector(state => state.users);

  const [fullUserData, setFullUserData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchUserData = async () => {
      if (!token) {
        navigate('/');
        return;
      }
      try{
        setLoading(true);
        setError(null);

        const data = await getUserProfile(token);
        setFullUserData(data);

      } catch (err) {
       console.error("Error fetching user profile:", err);
        setError(err.message || "No se pudo cargar la información del perfil.");
      } finally {
        setLoading(false);
      }
    };

    fetchUserData();
  }, [token, navigate]);


  if (loading) {
    return (
      <ProfileContainer>
        <ProfileTitle>Mi Perfil</ProfileTitle>
        <p style={{ textAlign: 'center' }}>Cargando perfil...</p>
      </ProfileContainer>
    );
  }

  if (error) {
    return (
      <ProfileContainer>
        <ProfileTitle>Mi Perfil</ProfileTitle>
        <ErrorMessageUI style={{ textAlign: 'center' }}>{error}</ErrorMessageUI>
      </ProfileContainer>
    );
  }


  if (!fullUserData) {
    return (
      <ProfileContainer>
        <ProfileTitle>Mi Perfil</ProfileTitle>
        <p style={{ textAlign: 'center' }}>No se pudo cargar la información del perfil. Por favor, intenta de nuevo.</p>
      </ProfileContainer>
    );
  }

  return (
    <>
    {/*<ProfileContainer>*/}
    {/*</ProfileContainer>*/}

    <ProfileContainer>
        <ProfileTitle>Información Personal</ProfileTitle>
        <ProfileSection>
          <ProfileDetailRow> <strong>NOMBRE:</strong>{fullUserData.data.name}</ProfileDetailRow>
          <ProfileDetailRow><strong>APELLIDO:</strong> {fullUserData.data.lastname}</ProfileDetailRow>
          <ProfileDetailRow><strong>EMAIL:</strong> {fullUserData.data.email}</ProfileDetailRow>
          <ProfileDetailRow><strong>EDAD:</strong> {fullUserData.data.age|| 'N/A'}</ProfileDetailRow>
          <ProfileDetailRow><strong>GÉNERO:</strong> {fullUserData.data.gender || 'N/A'}</ProfileDetailRow>
          <ProfileDetailRow>
            <strong>ZONAS:</strong>{" "}
            {fullUserData.data.zones
                .map(zone =>
                    zone
                        .toLowerCase()
                        .split("_")
                        .map(word => word.charAt(0).toUpperCase() + word.slice(1))
                        .join(" ")
                )
                .join(", ")}
          </ProfileDetailRow>
        </ProfileSection>
    </ProfileContainer>

    </>
  );
};

export default UserProfilePage;