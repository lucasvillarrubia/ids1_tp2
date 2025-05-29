import { styled } from "styled-components";

export const UserInfoUI = styled.div`
        display: flex;
        flex-direction: column;
        gap: 10px;
        padding-left: 20px;
        font-size: 20px;
        font-weight: 800;
        & a {
                color: var(--marronzote);
                font-size: 15px;
                font-weight: 600;
        }
        & a:hover {
                text-decoration: underline;
        }
`;


export const UserMenuBG = styled.div`
        position: fixed;
        top: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.7);
        z-index: 2;
`;

export const UserMenuUI = styled.div`
        position: fixed;
        top: 25%;
        right: 0;
        width: 25vw;
        height: 25vh;
        min-width: 320px;
        /* background-color: rgb(0, 0, 0); */
        background-color: var(--marronzote);
        backdrop-filter: blur(10px);
        z-index: 3;
        /* padding-top: 20px; */
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: baseline;
        gap: 10px;
        padding-left: 30px;
        border-bottom-left-radius: 20px;
        border-top-left-radius: 20px;
        & p {
                color: white;
                font-size: 25px;
                /* width: 100%; */
                text-align: left;
                cursor: pointer;
        }
        & h6 {
                color: white;
                font-size: 30px;
                /* width: 100%; */
                text-align: left;
        }
        & p:hover {
                text-decoration: underline;
        }
        @media screen and (max-width: 768px) {
                background-color: rgba(0, 0, 0, 0.7);
        }
`;
