import styled from "styled-components"

export const NavbarUI = styled.div`
        width: 100%;
        background-color: white;
        color: white;
        display: flex;
        justify-content: center;
        align-items: center;
        padding: 5px 0;
        position: fixed;
        top: 0;
        @media screen and (min-width: 768px) {
                justify-content: space-between;
                padding: 15px;
        }
        z-index: 1;
`;

export const NavTitle = styled.p`
        font-family: 'Shrikhand', sans-serif;
        color: var(--rosa);
        font-size: 20px;
        letter-spacing: 3px;
        cursor: pointer;
`;

export const ToolbarUI = styled.div`
        width: 100%;
        background-color: black;
        display: flex;
        justify-content: space-around;
        align-items: center;
        position: fixed;
        bottom: 0;
        padding: 10px 0 5px 0;
        @media screen and (min-width: 768px) {
                display: none;
        }
        z-index: 1;
`;

export const NavIcon = styled.a`
        font-size: 30px;
        color: black;
        display: flex;
        justify-content: center;
        align-items: center;
        cursor: pointer;
        & span {
                width: 25px;
                height: 25px;
                border-radius: 20px;
                background-color: red;
                font-size: 18px;
                line-height: 20px;
                text-align: center;
                font-weight: 800;
                @media screen and (max-width: 768px) {
                        display: none;
                }
        }
`;

export const NavLogo = styled.img`
        height: 50px;
        cursor: pointer;
`;

export const NavMenu = styled.div`
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        justify-content: space-evenly;
        word-wrap: break-word;
        align-items: center;
        display: none;
        @media screen and (min-width: 768px) {
                display: flex;
                justify-content: space-between;
                gap: 20px;
        }
`;

export const NavMenuItem = styled.a`
        font-weight: bold;
`;

export const NavMenuButton = styled.button`
        box-shadow: 11px 28px 50px rgba(20, 78, 90, 0.2);
        border-radius: 15px;
        width: 166px;
        cursor: pointer;
        height: 50px;
        color: white;
        font-family: 'Montserrat';
        font-weight: 700;
        font-size: 14px;
        border: none;
`;