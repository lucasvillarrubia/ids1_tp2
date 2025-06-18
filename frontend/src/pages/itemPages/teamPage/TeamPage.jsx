import React from 'react';
import { ItemPageInfo, ItemPageAuthor, ItemPageTitle } from '../itemPagesStyles.js';
import { useParams } from "react-router"
import PlayerCard from './PlayerCard.jsx';
import {ActionButton, ButtonContainer, ExpandedItemCardUI, PlayersList, TeamContainer} from "../itemPagesStyles.js";

const TeamPage = () => {
    // if (!team || typeof team !== 'object') return null;

    const { teamId } = useParams();
    // const team = useSelector(state => state.teams[teamId]);

    const team = {
        id: "team123",
        name: "Los Leones FC",
        coach: "Carlos Gómez",
        founded: 1998,
        country: "Argentina",
        division: "Primera B",
        players: [
            { id: 1, name: "Juan Pérez", position: "Delantero" },
            { id: 2, name: "Luis García", position: "Arquero" },
        ],
    };

    const handleAddPlayer = () => {
    };

    const handleRemovePlayer = () => {
    };

    return (
        <TeamContainer>
            <ExpandedItemCardUI>
                <ItemPageInfo>
                    <ItemPageTitle>{team.name || 'Team Name'}</ItemPageTitle>
                    {Object.entries(team).map(([key, value]) => (
                        <ItemPageAuthor key={key}>
                            {key}: {typeof value === 'object' ? JSON.stringify(value) : String(value)}
                        </ItemPageAuthor>
                    ))}
                </ItemPageInfo>
                <ButtonContainer>
                    <ActionButton onClick={handleAddPlayer}>Add Player</ActionButton>
                    <ActionButton onClick={handleRemovePlayer}>Remove Player</ActionButton>
                </ButtonContainer>
            </ExpandedItemCardUI>

            <PlayersList>
                {(team.players || []).map((player, idx) => (
                    <PlayerCard key={idx} player={player} />
                ))}
            </PlayersList>
        </TeamContainer>
    );
};

export default TeamPage;
