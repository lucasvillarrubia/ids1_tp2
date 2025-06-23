import React, { useState, useEffect } from 'react'
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
import {fetchMatches} from "../../../features/matches/matchesSlice.js";

const MatchPage = () => {
    const { matchId } = useParams();
    const dispatch = useDispatch();
    useEffect(() => {
        dispatch(fetchMatches());
    }, [dispatch]);
    const navigate = useNavigate();
    const [editing, setEditing] = useState(false);
    const { currentUser } = useSelector(state => state.users);

    const match = useSelector(state => {
        const idNum = Number(matchId)
        return state.matches.list.content.find(m => m.id === idNum)
    })

    if (!match) return <p>Partido no encontrado.</p>

    const handleJoin = () => {
        try {
            dispatch(joinMatch(match.id))
            navigate(`/me`);
        } catch (error) {
            console.error("Error al unirse al partido:", error);
            alert("No se pudo unirse al partido.");
            return;
        }
    }
    const handleLeave = () => {
        try {
            dispatch(leaveMatch(match.id))
            navigate(`/me`);
        } catch (error) {
            console.error("Error al abandonar el partido:", error);
            alert("No se pudo abandonar el partido.");
            return;
        }
    }

    const handleStart = () => {
        try {
            if (!confirm('Vas a iniciar este partido')) return;
            dispatch(startMatch(match.id));
            navigate(`/me`);
        } catch (error) {
            console.error("Error al iniciar el partido:", error);
            alert("No se pudo iniciar el partido.");
            return;
        }
    }

    const handleClose = () => {
        try {
            if (!confirm('Estás seguro de que querés cerrar este partido?')) return;
            dispatch(closeMatch(match.id))
            navigate('/me');
        } catch (error) {
            console.error("Error al cerrar el partido:", error);
            alert("No se pudo cerrar el partido.");
            return;
        }
    }

    const handleDelete = () => {
        if (!confirm('Estás seguro de que querés eliminar este partido?')) return;
        dispatch(deleteMatch(match.id))
    }

    // console.log("usuario actual:");
    // console.log(currentUser);
    // console.log("partido:");
    // console.log(match);
    // console.log("email organizador?");
    // console.log(match.reservation.organizerEmail);
    // console.log("participantes:");
    // console.log(match.participationType.players);
    let iAmInMatch = match.participationType.players.includes(currentUser.email);
    let iAmOwner = match.reservation.organizerEmail === currentUser.email;

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
                    {!iAmInMatch && <ActionButton onClick={handleJoin}>UNIRSE</ActionButton>}
                    {iAmInMatch && <ActionButton onClick={handleLeave}>ABANDONAR</ActionButton>}
                </ButtonContainer>
                <ButtonContainer>
                    {iAmOwner && <ActionButton onClick={handleStart}>Iniciar</ActionButton>}
                    {iAmOwner && <ActionButton onClick={() => setEditing(true)}>Editar</ActionButton>}
                    {iAmOwner && <ActionButton onClick={handleClose}>Cerrar</ActionButton>}
                    {iAmOwner && <ActionButton onClick={handleDelete}>Eliminar</ActionButton>}
                </ButtonContainer>
            </ExpandedItemCardUI>
            <ActionButton onClick={() => navigate('/me')}>Volver a mi perfíl</ActionButton>
        </MatchContainer>
    )
}

export default MatchPage
