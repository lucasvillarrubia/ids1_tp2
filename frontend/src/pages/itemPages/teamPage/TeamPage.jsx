import React from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';

import PlayerCard from './PlayerCard.jsx';
import { ItemPageInfo, ItemPageAuthor, ItemPageTitle, ActionButton, ButtonContainer, ExpandedItemCardUI, PlayersList, TeamContainer } from '../itemPagesStyles.js';

const TeamPage = () => {
    const { teamId } = useParams();
    const navigate = useNavigate();
    const team = useSelector(state => state.teams.list[decodeURIComponent(teamId)]);

    if (!team) return <p>Equipo no encontrado.</p>;

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
                    <ActionButton onClick={handleAddPlayer}>Agregar jugador</ActionButton>
                    <ActionButton onClick={handleRemovePlayer}>Eliminar jugador</ActionButton>
                </ButtonContainer>
            </ExpandedItemCardUI>

            <PlayersList>
                {(team.players || []).map((playerName, idx) => (
                    <PlayerCard key={idx} player={{ name: playerName }} />
                ))}
            </PlayersList>
            <ActionButton onClick={() => navigate('/me')}>Volver a mi perfíl</ActionButton>
        </TeamContainer>
    );
};

export default TeamPage;
