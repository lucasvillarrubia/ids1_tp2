import React, { useState } from 'react'
import { useParams, useNavigate } from 'react-router-dom'
import { useSelector, useDispatch } from 'react-redux'

import {
    ItemPageInfo,
    ItemPageAuthor,
    ItemPageTitle,
    ActionButton,
    ButtonContainer,
    ExpandedItemCardUI,
    MatchContainer,
    FieldContainer, PlayersList
} from '../itemPagesStyles.js'

import { joinMatch, leaveMatch, startMatch, closeMatch, updateMatch, deleteMatch } from '../../../features/matches/matchesAPI.js'
import ItemsForm from "../../../components/itemsForm/ItemsForm.jsx";
import PlayerCard from "../teamPage/PlayerCard.jsx";

const MatchPage = () => {
    const { matchId } = useParams();
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const [editing, setEditing] = useState(false);

    const match = useSelector(state => {
        const idNum = Number(matchId)
        return state.matches.list.content.find(m => m.id === idNum)
    })

    if (!match) return <p>Partido no encontrado.</p>

    const handleJoin = () => dispatch(joinMatch(match.id))
    const handleLeave = () => dispatch(leaveMatch(match.id))

    const handleStart = () => {
        if (!confirm('Vas a iniciar este partido')) return;
        dispatch(startMatch(match.id))
    }

    const handleClose = () => {
        if (!confirm('Estás seguro de que querés cerrar este partido?')) return;
        dispatch(closeMatch(match.id))
    }

    const handleDelete = () => {
        if (!confirm('Estás seguro de que querés eliminar este partido?')) return;
        dispatch(deleteMatch(match.id))
    }

    return editing ? (
        <MatchContainer>
            <ItemsForm key="updateMatch" itemCategory="updateMatch" onCancel={() => setEditing(false)} existingItem={match} />
        </MatchContainer>
    ) : (
        <MatchContainer>
            <ExpandedItemCardUI>
                <ItemPageInfo>
                    <ItemPageTitle>Partido #{match.id}</ItemPageTitle>
                    <ItemPageAuthor>Estado: {match.state}</ItemPageAuthor>
                    <ItemPageAuthor>Tipo de participación: {match.participationType?.type}</ItemPageAuthor>
                </ItemPageInfo>
                <PlayersList>
                    {(match.participationType?.players || []).map((playerName, idx) => (
                        <PlayerCard key={idx} player={{ name: playerName }} idx={idx} />
                    ))}
                </PlayersList>
                <ButtonContainer>
                    <ActionButton onClick={handleJoin}>Agregar Jugador</ActionButton>
                    <ActionButton onClick={handleLeave}>Eliminar Jugador</ActionButton>
                </ButtonContainer>
                <ButtonContainer>
                    <ActionButton onClick={handleStart}>Iniciar</ActionButton>
                    <ActionButton onClick={() => setEditing(true)}>Editar</ActionButton>
                    <ActionButton onClick={handleClose}>Cerrar</ActionButton>
                    <ActionButton onClick={handleDelete}>Eliminar</ActionButton>
                </ButtonContainer>
            </ExpandedItemCardUI>
            <ActionButton onClick={() => navigate('/me')}>Volver a mi perfíl</ActionButton>
        </MatchContainer>
    )
}

export default MatchPage
