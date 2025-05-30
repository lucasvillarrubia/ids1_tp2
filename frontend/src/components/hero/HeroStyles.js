import styled from "styled-components";

export const HeroUI = styled.div`
        width: 100%;
        max-width: 100%;
        height: 100vh;
        background-color: var(--marroncito);
        background-repeat: repeat;
        background-size: 50%;
        /* background-image: url('./images/hero2.jpg'); */
`;

export const HeroBGBlur = styled.div`
        width: 100%;
        height: 100%;
        background: linear-gradient(0deg, rgba(0,0,0,0) 0%, rgba(0,0,0,0.9) 50%, rgba(0,0,0,1) 100%);
        padding: 10px;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        flex-wrap: wrap;
        padding: 20px;
        gap: 30px;
`;

export const HeroTitle = styled.h1`
        font-size: 15vw;
        font-family: 'Bebas Neue', sans-serif;
        color: white;
        /* font-style: italic; */
        text-align: center;
        /* font-weight: bold; */
        letter-spacing: 3px;
        word-wrap: break-word;
        word-break: break-all;
        /* text-overflow: ; */
        line-height: 15vw;
        /* width: 75vw; */
`;

export const HeroButton = styled.button`
        padding: 10px 20px;
        font-family: 'Bebas Neue', sans-serif;
        font-size: 15px;
        border-radius: 10px;
        border: none;
        cursor: pointer;
`;