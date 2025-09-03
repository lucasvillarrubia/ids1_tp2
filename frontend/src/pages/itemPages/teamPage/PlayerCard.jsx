import React from 'react';
import {PlayerCardUI} from "../itemPagesStyles.js";


const PlayerCard = ({ player, idx }) => {
    if (!player) return null;
    return (
        <PlayerCardUI>
            <p>Jugador {idx+1}</p>
            <h4>{player.name}</h4>
            {player.position && <p>Position: {player.position}</p>}
        </PlayerCardUI>
    );
};

export default PlayerCard;
