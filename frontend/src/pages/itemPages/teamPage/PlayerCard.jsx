import React from 'react';
import {PlayerCardUI} from "../itemPagesStyles.js";


const PlayerCard = ({ player }) => {
    if (!player) return null;
    return (
        <PlayerCardUI>
            <h4>{player.name}</h4>
            <p>Position: {player.position}</p>
        </PlayerCardUI>
    );
};

export default PlayerCard;
