import { styled } from "styled-components";

export const NotFoundBG = styled.section`
        background: linear-gradient(0deg, var(--marroncito) 0%, black 90%, black 100%);
        height: 100vh;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        gap: 50px;
        font-size: 100px;
        line-height: 100px;
        text-shadow: var(--borde_texto);
        color: white;
        & p {
                font-family: 'Shrikhand', sans-serif;
        }
        @media screen and (max-width: 768px) {
                font-size: 50px;
                line-height: 60px;
                text-align: center;
        }
`;

export const NotFoundButton = styled.button`
        cursor: pointer;
        border: 1px solid black;
        padding: 1rem 3rem;
        border-radius: 10px;
        font-size: 15px;
        font-family: 'Shrikhand', sans-serif;
        text-transform: uppercase;
        text-align: center;
        background: white;
        color: black;
        @media screen and (min-width: 768px) {
                font-size: 20px;
        }
`;