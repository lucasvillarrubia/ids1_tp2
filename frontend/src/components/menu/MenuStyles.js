import styled from "styled-components"

export const MenuBG = styled.div`
        position: fixed;
        top: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.7);
        backdrop-filter: blur(10px);
        z-index: 2;
`;

export const MenuUI = styled.ul`
        position: fixed;
        top: 0;
        right: 0;
        width: 25vw;
        min-width: 320px;
        height: 100%;
        background-color: rgb(0, 0, 0);
        backdrop-filter: blur(10px);
        z-index: 3;
        /* padding-top: 20px; */
        display: flex;
        flex-direction: column;
        justify-content: last baseline;
        align-items: end;
        gap: 10px;
        margin-top: 20px;
        text-align: right;
        & a {
                font-size: 30px;
                font-family: 'Kanit', sans-serif;
                color: white;
                padding-right: 50px;
                @media screen and (max-width: 768px) {
                        width: 100%;
                        padding-right: 20px;
                }
        }
        & p {
            font-size: 30px;
            font-family: 'Kanit', sans-serif;
            color: white;
            padding-right: 50px;
            @media screen and (max-width: 768px) {
                width: 100%;
                padding-right: 20px;
            }
        }
        /* & li {
                font-size: 30px;
                font-family: 'Kanit', sans-serif;
                color: white;
                padding-right: 50px;
                @media screen and (max-width: 768px) {
                        width: 100%;
                        padding-right: 20px;
                }
        } */
        & a:hover {
                text-decoration: underline;
                @media screen and (max-width: 768px) {
                        background-color: white;
                        color: black;
                        text-decoration: none;
                }
        }
        & p:hover {
            text-decoration: underline;
            @media screen and (max-width: 768px) {
                background-color: white;
                color: black;
                text-decoration: none;
            }
        }
        /* & li:hover {
                text-decoration: underline;
                @media screen and (max-width: 768px) {
                        background-color: white;
                        & a {
                                color: black;
                        }
                }
        } */
        @media screen and (max-width: 768px) {
                background-color: rgba(0, 0, 0, 0.7);
        }
`;

export const MenuExit = styled.button`
        height: 25px;
        width: 25px;
        font-size: 10px;
        border-radius: 5px;
        font-weight: bold;
        position: relative;
        background: linear-gradient(98.81deg, var(--tomato) -0.82%, var(--marronzote) 101.53%);
        box-shadow: 11px 28px 50px rgba(20, 78, 90, 0.2);
        cursor: pointer;
        color: white;
        font-family: 'Kanit', sans-serif;
        border: none;
        margin-top: 30px;
        margin-right: 50px;
        margin-bottom: 30px;
        @media screen and (max-width: 768px) {
                margin-top: 0;
                margin-right: 20px;
        }
`;