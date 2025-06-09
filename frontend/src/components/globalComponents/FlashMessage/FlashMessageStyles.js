import styled, { css } from 'styled-components';

export const Overlay = styled.div`
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.6);
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 1000;
`;

const typeStyles = {
    info: css`background-color: #007BFF;`,
    success: css`background-color: #28a745;`,
    error: css`background-color: #dc3545;`,
    warning: css`background-color: #ffc107; color: black;`,
};

export const MessageBox = styled.div`
    ${({ type }) => typeStyles[type] || typeStyles.info}
    color: white;
    padding: 30px;
    font-family: 'Bebas Neue', sans-serif;
    text-transform: uppercase;
    border-radius: 15px;
    width: 300px;
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 20px;
    text-align: center;
    font-size: 15px;
    & p {
        font-family: 'Bebas Neue', sans-serif;
        text-transform: uppercase;
    }
`;

export const OKButton = styled.button`
    background-color: white;
    color: black;
    padding: 10px 20px;
    border: none;
    font-family: 'Bebas Neue', sans-serif;
    text-transform: uppercase;
    border-radius: 50px;
    cursor: pointer;
    font-weight: bold;
    font-size: 14px;
    letter-spacing: 2px;
    &:hover {
        background-color: black;
        color: white;
        border: 2px solid white;
        transition: all 0.2s ease-out;
    }
`;