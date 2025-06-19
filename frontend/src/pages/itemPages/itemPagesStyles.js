import styled from "styled-components";

export const TeamContainer = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 30px;
`;

export const MatchContainer = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 30px;
`;

export const FieldContainer = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 30px;
    background-color: var(--oldgold);
    margin-top: 200px;
    & h3 {
        color: white;
    }
`;

const ItemCardUI = styled.div`
        width: 300px;
        /* min-height: 500px; */
        /* background-color: var(--marroncito); */
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        gap: 10px;
`;

export const ExpandedItemCardUI = styled(ItemCardUI)`
    width: 70vw;
    margin-top: 200px;
    font-family: Bebas Neue, sans-serif;
    background-color: black;
    padding: 50px;
    border-radius: 10px;
`;

export const ButtonContainer = styled.div`
    display: flex;
    justify-content: center;
    gap: 20px;
    margin-top: 20px;
`;

export const ActionButton = styled.button`
    padding: 10px 20px;
    background-color: var(--mentita);
    color: black;
    border: solid black 1px;
    border-radius: 15px;
    font-family: Bebas Neue, sans-serif;
    cursor: pointer;
    font-size: 14px;
`;

export const PlayersList = styled.div`
    display: flex;
    flex-wrap: wrap;
    gap: 15px;
    justify-content: center;
`;

export const ItemPageInfo = styled.div`
        background-color: black;
        width: 100%;
        border-radius: 10px;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        align-items: center;
        color: white;
        min-height: 375px;
        /* padding: 10px 20px 0; */
        & img {
                max-width: 90%;
                padding: 20px 5px;
                max-height: 200px;
        }
`;


export const ItemPageTitle = styled.h3`
        font-size: 30px;
        width: 100%;
        text-align: left;
        padding-left: 25px;
        padding-right: 25px;
        margin-bottom: 10px;
        line-height: 30px;
`;

export const ItemPageAuthor = styled.h4`
        font-size: 15px;
        width: 100%;
        text-align: left;
        padding: 0 25px;
        padding-top: 10px;
        letter-spacing: 2px;
`;

export const PlayerCardUI = styled.div`
    background-color: black;
    color: white;
    padding: 10px;
    border-radius: 10px;
    width: 200px;
`;