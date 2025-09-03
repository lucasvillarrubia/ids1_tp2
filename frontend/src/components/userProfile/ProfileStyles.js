import { styled } from "styled-components";

export const ProfileItemUI = styled.div`
        /* background-color: var(--marronzote); */
        padding: 20px;
        /* border-bottom-right-radius: 15px; */
        display: flex;
        flex-direction: column;
        justify-content: baseline;
        gap: 20px;
        /* animation: show-left 0.5s ease-out; */
        color: black;
        & a {
                color: #909090;
                font-size: 12px;
        }
        /* @media screen and (min-width: 768px) {
                width: 400px;
        } */
`;

export const ProfileSectionTitle = styled.h3`
        font-size: 15px;
        letter-spacing: 1px;
        color: black;
        /* text-shadow: var(--borde_fino); */
        font-family: 'Bebas Neue', sans-serif;
        text-overflow: ellipsis;
        word-wrap: break-word;
        word-break: break-all;
        max-width: 100%;
        @media screen and (min-width: 768px) {
                font-size: 30px;
                /* margin-bottom: 50px; */
        }
`;

export const ProfileItemCardsContainer = styled.div`
        display: flex;
        flex-direction: column;
        justify-content: center;
        gap: 10px;
        align-items: center;
        /* text-align: left; */
        /* margin-top: 20px; */
        /* max-height: 160px; */
        /* overflow-y: scroll; */
        &::-webkit-scrollbar {
                width: 10px;
                background-color: #252525;
                border-radius: 5px;
        }
        &::-webkit-scrollbar-button {
                display: none;
        }
        &::-webkit-scrollbar-thumb {
                background-color: #606060;
                border-radius: 5px;
        }
        @media screen and (max-width: 768px) {       
                & p {
                        /* color: #909090; */
                        font-size: 12px;
                        /* width: 110px; */
                }
        }
`;





/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////       CARD       //////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

export const ProfileItemCardUI = styled.div`
        background-color: black;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: baseline;
        width: 60vw;
        height: 200px;
        /* background: #353535; */
        border-radius: 15px;
        padding: 20px 50px;
        gap: 10px;
        cursor: pointer;
        & img {
                max-width: 100px;
                height: 80px;
                object-fit: cover;
                /* padding-left: 15px; */
        }
        @media screen and (max-width: 768px) {
                width: 280px;
                height: 100px;
                & img {
                        max-width: 80px;
                        height: 50px;
                }
        }
`;

export const ProfileItemCardNumber = styled.p`
        font-size: 25px;
        text-overflow: ellipsis;
        line-height: 25px;
        color: white;
`; 

export const ProfileItemCardDate = styled.p`
        font-size: 20px;
        font-weight: bold;   
        color: white;
`;

export const ProfileItemCardTotal = styled.span`
        /* width: 20px; */
        /* height: 25px; */
        color: #FFFFFF;
        /* text-align: center; */
        font-size: 15px;
        line-height: 22px;
`;
