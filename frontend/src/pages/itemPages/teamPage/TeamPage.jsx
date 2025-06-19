import React from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';

import PlayerCard from './PlayerCard.jsx';
import { ItemPageInfo, ItemPageAuthor, ItemPageTitle, ActionButton, ButtonContainer, ExpandedItemCardUI, PlayersList, TeamContainer } from '../itemPagesStyles.js';

const TeamPage = () => {
    const { teamId } = useParams();
    const navigate = useNavigate();
    console.log(useSelector(state => state.teams.list));
    console.log(useSelector(state => state.teams));
    // const team = useSelector(state => state.teams.list[decodeURIComponent(teamId)]);

    const team = useSelector(state =>
        state.teams.list.find(team => team.name === decodeURIComponent(teamId))
    );

    if (!team) return <p>Equipo no encontrado.</p>;

    const handleEditTeam = () => {
    }

    const handleDeleteTeam = () => {
    }

    const handleAddPlayer = () => {
    };

    const handleRemovePlayer = () => {
    };

    return (
        <TeamContainer>
            <ExpandedItemCardUI>
                <ItemPageInfo>
                    <ItemPageTitle>{team.name}</ItemPageTitle>
                    <ItemPageAuthor>Capitán: {team.captain}</ItemPageAuthor>
                    {team.colors && <ItemPageAuthor>Colores: {team.colors}</ItemPageAuthor>}
                    {team.skill && <ItemPageAuthor>Habilidad: {team.skill}</ItemPageAuthor>}
                    {team.logo && <ItemPageAuthor>Logo: {team.logo}</ItemPageAuthor>}
                </ItemPageInfo>
                <ButtonContainer>
                    <ActionButton onClick={handleEditTeam}>Editar Equipo</ActionButton>
                    <ActionButton onClick={handleDeleteTeam}>Eliminar equipo</ActionButton>
                </ButtonContainer>
            </ExpandedItemCardUI>

            <PlayersList>
                {(team.players || []).map((playerName, idx) => (
                    <PlayerCard key={idx} player={{ name: playerName }} idx={idx} />
                ))}
            </PlayersList>
            <ButtonContainer>
                <ActionButton onClick={handleAddPlayer}>Agregar Jugador</ActionButton>
                <ActionButton onClick={handleRemovePlayer}>Agregar Jugador</ActionButton>
            </ButtonContainer>
            <ActionButton onClick={() => navigate('/me')}>Volver a mi perfíl</ActionButton>
        </TeamContainer>
    );
};

export default TeamPage;
