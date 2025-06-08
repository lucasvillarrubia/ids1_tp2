
import styled from "styled-components";


export const ProductCardsContainer = styled.div`
        /* background-color: var(--rosa); */
        display: grid;
        width: 100%;
        grid-template-columns: repeat(auto-fit, 300px);
        gap: 20px;
        justify-content: center;
        align-items: center;
        padding: 50px;
`;

export const ProductCardUI = styled.div`
        width: 300px;
        /* min-height: 500px; */
        /* background-color: var(--marroncito); */
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        gap: 10px;
`;

export const AddInstanceButtonUI = styled.button`
        width: 300px;
        min-height: 375px;
        background-color: black;
        color: white;
        border: none;
        cursor: pointer;
        border-radius: 10px;
        letter-spacing: 3px;
        font-size: 30px;
        &:hover {
                background-color: white;
                color: black;
                /* border: 3px solid black; */
                font-weight: bold;
                border-radius: 10px;
                font-size: 35px;
                letter-spacing: 2px;
                transition: all ease-out 0.2s;
                width: 100%;
        }
`;

export const ProductInfo = styled.div`
        background-color: black;
        width: 100%;
        border-radius: 10px;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        align-items: center;
        color: white;
        min-height: 375px;
        /* padding: 10px 20px 0; */
        & img {
                max-width: 90%;
                padding: 20px 5px;
                max-height: 200px;
        }
`;


export const ProductTitle = styled.h3`
        font-size: 30px;
        width: 100%;
        text-align: left;
        padding-left: 25px;
        padding-right: 25px;
        margin-bottom: 10px;
        line-height: 30px;
`;

export const ProductAuthor = styled.h4`
        font-size: 15px;
        width: 100%;
        text-align: left;
        padding: 0 25px;
        padding-top: 10px;
        letter-spacing: 2px;
`;

export const ProductPrice = styled.h4`
        font-size: 20px;
        color: black;
        background-color: white;
        border-bottom-left-radius: 8px;
        border-bottom-right-radius: 8px;
        padding: 5px 25px;
        letter-spacing: 2px;
        width: 100%;
`;

export const ProductButton = styled.button`
        width: 90%;
        height: 40px;
        background-color: black;
        color: white;
        border: none;
        cursor: pointer;
        border-radius: 50px;
        letter-spacing: 3px;
        font-size: 13px;
        &:hover {
                background-color: white;
                color: black;
                /* border: 3px solid black; */
                font-weight: bold;
                border-radius: 10px;
                font-size: 16px;
                letter-spacing: 2px;
                transition: all ease-out 0.2s;
                width: 100%;
        }
`;

export const ProductsSectionButton = styled.button`
        padding: 10px 15px;
        font-family: 'Shrikhand', sans-serif;
        font-size: 15px;
        line-height: 15px;
        border-radius: 10px;
        border: none;
        cursor: pointer;
        @media screen and (min-width: 768px) {
                font-size: 25px;
                line-height: 25px;
        }
        &:hover {
                background-color: var(--vanilla);
                border: 1px black solid;
                line-height: 23px;
                padding: 10px 14px;
        }
`;

export const ButtonContainer = styled.div`
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        gap: 25px;
        @media screen and (min-width: 768px) {
                gap: 50px;
        }
        padding: 10px 0;
`;