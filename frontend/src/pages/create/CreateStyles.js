import { styled } from "styled-components";

export const CreateBG = styled.section`
        background-color: var(--marroncito);
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        max-width: 100%;
        padding-top: 150px;
        @media screen and (max-width: 768px) {
                padding-top: 100px;
                /* font-size: 20px; */
        }
`;

export const CreateContainer = styled.div`
        display: flex;
        flex-direction: row;
        justify-content: space-evenly;
        flex-wrap: wrap;
        align-items: baseline;
        width: 100%;
        @media screen and (max-width: 768px) {
                flex-direction: column;
                align-items: center;
                gap: 20px;
        }
`;

export const CreateInfo = styled.div`
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        gap: 20px;
        /* & input {
                margin: 0 20px;
        } */
        @media screen and (min-width: 768px) {
                justify-content: baseline;
                align-items: baseline;
        }
`;

export const CreateTitle = styled.h2`
        font-size: 20px;
        letter-spacing: 1px;
        color: black;
        /* text-shadow: var(--borde_fino); */
        font-family: 'Raleway', sans-serif;
        text-overflow: ellipsis;
        word-wrap: break-word;
        word-break: break-all;
        max-width: 100%;
        @media screen and (min-width: 768px) {
                font-size: 30px;
                margin-bottom: 50px;
        }
`;

export const CreateSubtitle = styled.h3`
        font-size: 20px;
        letter-spacing: 1px;
        color: white;
        text-shadow: var(--borde_fino);
        font-family: 'Raleway', sans-serif;
        text-overflow: ellipsis;
        word-wrap: break-word;
        max-width: 80%;
        text-align: center;
        margin-top: 20px;
        @media screen and (min-width: 768px) {
                font-size: 20px;
                padding: 20px 0;
                text-align: left;
                max-width: 100%;
        }
`;

export const CreateButton = styled.button`
        cursor: pointer;
        border: 2px solid black;
        padding: 1rem 3rem;
        border-radius: 30px;
        font-size: 15px;
        font-family: 'Shrikhand', sans-serif;
        text-transform: uppercase;
        text-align: center;
        background: var(--vanilla);
        color: black;
        @media screen and (min-width: 768px) {
                font-size: 20px;
        }
`;




