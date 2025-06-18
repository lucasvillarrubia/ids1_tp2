import { styled } from "styled-components";

export const PhCardBG = styled.section`
        background: linear-gradient(0deg, var(--marroncito) 0%, var(--mentita) 20%, var(--saucecito) 90%, var(--saucecito) 100%);
        height: 100vh;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        gap: 50px;
        font-size: 60px;
        line-height: 100px;
        /* text-shadow: var(--borde_texto); */
        letter-spacing: 3px;
        color: black;
        & p {
                font-family: 'Shrikhand', sans-serif;
        }
        @media screen and (max-width: 768px) {
                font-size: 30px;
                line-height: 40px;
                text-align: center;
                letter-spacing: 1px;
                gap: 20px;
        }
`;

export const PhCardButton = styled.button`
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
        min-width: 250px;
        @media screen and (min-width: 768px) {
                font-size: 20px;
                min-width: 400px;
        }
`;