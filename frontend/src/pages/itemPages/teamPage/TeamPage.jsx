import React, { useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';

import PlayerCard from './PlayerCard.jsx';
import {
    ItemPageInfo,
    ItemPageAuthor,
    ItemPageTitle,
    ActionButton,
    ButtonContainer,
    ExpandedItemCardUI,
    PlayersList,
    TeamContainer,
    MatchContainer
} from '../itemPagesStyles.js';
import ItemsForm from "../../../components/itemsForm/ItemsForm.jsx";
import {addPlayerThunk, deleteTeamThunk, removePlayerThunk} from "../../../features/teams/teamsSlice.js";

const TeamPage = () => {
    const { teamId } = useParams();
    const [editing, setEditing] = useState(false);
    const navigate = useNavigate();
    const { dispatch } = useDispatch();
    const { currentUser } = useSelector(state => state.users);
    // const team = useSelector(state => state.teams.list[decodeURIComponent(teamId)]);

    const team = useSelector(state =>
        state.teams.list.find(team => team.name === decodeURIComponent(teamId))
    );

    if (!team) return <p>Equipo no encontrado.</p>;

    const handleDeleteTeam = async () => {
        const confirm = window.confirm(`¿Estás seguro de que querés eliminar el equipo "${team.name}"?`);
        if (!confirm) return;

        try {
            await dispatch(deleteTeamThunk(team.name));
            navigate('/me');
        } catch (error) {
            console.error("Error al eliminar el equipo:", error);
            alert("No se pudo eliminar el equipo.");
        }
    };

    const handleAddPlayer = async () => {
        const playerName = prompt("Nombre del jugador a agregar:");
        if (!playerName) return;

        try {
            await dispatch(addPlayerThunk({ id: team.name, playerName }));
            navigate('/me');

        } catch (error) {
            console.error("Error al agregar jugador:", error);
            alert("No se pudo agregar el jugador.");
        }
    };


    const handleRemovePlayer = async () => {
        const playerName = prompt("Nombre del jugador a eliminar:");
        if (!playerName) return;

        try {
            await dispatch(removePlayerThunk({ id: team.name, playerName }));
            navigate('/me');
        } catch (error) {
            console.error("Error al eliminar jugador:", error);
            alert("No se pudo eliminar el jugador.");
        }
    };

    let iAmCaptain = currentUser.email === team.captain;

    return editing ? (
        <TeamContainer>
            <ItemsForm key="updateTeam" itemCategory="updateTeam" onCancel={() => setEditing(false)} existingItem={team} />
        </TeamContainer>
    ) : (
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
                    {iAmCaptain && <ActionButton onClick={() => setEditing(true)}>Editar Equipo</ActionButton>}
                    {iAmCaptain && <ActionButton onClick={handleDeleteTeam}>Eliminar equipo</ActionButton>}
                </ButtonContainer>
            </ExpandedItemCardUI>

            <PlayersList>
                {(team.players || []).map((playerName, idx) => (
                    <PlayerCard key={idx} player={{ name: playerName }} idx={idx} />
                ))}
            </PlayersList>
            <ButtonContainer>
                {iAmCaptain && <ActionButton onClick={handleAddPlayer}>Agregar Jugador</ActionButton>}
                {iAmCaptain && <ActionButton onClick={handleRemovePlayer}>Eliminar Jugador</ActionButton>}
            </ButtonContainer>
            <ActionButton onClick={() => navigate('/me')}>Volver a mi perfíl</ActionButton>
        </TeamContainer>
    );
};

export default TeamPage;
