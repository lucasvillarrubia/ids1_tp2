import { styled } from "styled-components";

export const MyProfileUI = styled.div`
        background-color: var(--tomato);
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        max-width: 100%;
        margin-top: 30px;
        padding: 100px;
        gap: 30px;
        @media screen and (max-width: 768px) {
                padding-top: 100px;
        }
`;

export const MyProfileTitle = styled.h2`
        font-size: 32px;
        letter-spacing: 1px;
        color: black;
        font-family: 'Bebas Neue', sans-serif;
        text-overflow: ellipsis;
        word-wrap: break-word;
        word-break: break-all;
        max-width: 100%;
        @media screen and (min-width: 768px) {
                font-size: 30px;
        }
`;

export const MyProfileButton = styled.button`
        border: 2px solid black;
        padding: 1rem 3rem;
        border-radius: 30px;
        font-size: 30px;
        font-family: 'Bebas Neue', sans-serif;
        text-transform: uppercase;
        text-align: center;
        background: var(--amarillo);
        color: black;
        cursor: pointer;
`;
