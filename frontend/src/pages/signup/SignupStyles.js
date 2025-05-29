import { styled } from "styled-components";

export const SignupBG = styled.div`
        background: linear-gradient(0deg, var(--marroncito) 0%, var(--amarillo) 100%);
        /* background-color: var(--marroncito); */
        padding: 200px 20px;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        font-size: 25px;
`;

export const SignupTitle = styled.h2`
        font-family: 'Bebas Neue', sans-serif;
        text-align: center;
        overflow-wrap: break-word;
        font-size: 60px;
        color: white;
        text-shadow: var(--borde_fino);
        line-height: 50px;
        margin-bottom: 50px;
        letter-spacing: 1px;
        @media screen and (max-width: 768px) {
                font-size: 50px;
                max-width: none;
                line-height: 60px;
                margin: 0;
        }
`;

