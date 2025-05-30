import styled from "styled-components";

export const HomeSection = styled.section`
        background-color: var(--marroncito);
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        max-width: 100%;
`;

export const HomeTitle = styled.h2`
        font-size: 30px;
        letter-spacing: 5px;
        color: white;
        text-shadow: var(--borde_fino);
        font-family: 'Bebas Neue', sans-serif;
        text-overflow: ellipsis;
        word-wrap: break-word;
        word-break: break-all;
        max-width: 100%;
`;