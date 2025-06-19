import React from 'react'
import { useParams, useNavigate } from 'react-router-dom'
import { useSelector, useDispatch } from 'react-redux'

import { ItemPageInfo, ItemPageAuthor, ItemPageTitle, ActionButton, ButtonContainer, ExpandedItemCardUI, MatchContainer } from '../itemPagesStyles.js'

import { joinMatch, leaveMatch, startMatch, closeMatch, updateMatch, deleteMatch } from '../../../features/matches/matchesAPI.js'

const MatchPage = () => {
    const { matchId } = useParams();
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const match = useSelector(state => {
        const idNum = Number(matchId)
        return state.matches.list.content.find(m => m.id === idNum)
    })

    if (!match) return <p>Partido no encontrado.</p>

    const handleJoin = () => dispatch(joinMatch(match.id))
    const handleLeave = () => dispatch(leaveMatch(match.id))
    const handleStart = () => dispatch(startMatch(match.id))
    const handleClose = () => dispatch(closeMatch(match.id))
    const handleDelete = () => dispatch(deleteMatch(match.id))

    return (
        <MatchContainer>
            <ExpandedItemCardUI>
                <ItemPageInfo>
                    <ItemPageTitle>Partido #{match.id}</ItemPageTitle>
                    <ItemPageAuthor>Estado: {match.state}</ItemPageAuthor>
                    <ItemPageAuthor>Tipo de participación: {match.participationType?.type}</ItemPageAuthor>
                </ItemPageInfo>
                <ButtonContainer>
                    <ActionButton onClick={handleJoin}>Unirse</ActionButton>
                    <ActionButton onClick={handleLeave}>Salir</ActionButton>
                    <ActionButton onClick={handleStart}>Iniciar</ActionButton>
                    <ActionButton onClick={handleClose}>Cerrar</ActionButton>
                    <ActionButton onClick={handleDelete}>Eliminar</ActionButton>
                </ButtonContainer>
            </ExpandedItemCardUI>
            <ActionButton onClick={() => navigate('/me')}>Volver a mi perfíl</ActionButton>
        </MatchContainer>
    )
}

export default MatchPage
