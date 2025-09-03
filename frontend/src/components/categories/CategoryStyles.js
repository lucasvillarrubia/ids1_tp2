import styled from 'styled-components'

export const CategoryCardUI = styled.div`
        width: 200px;
        height: 200px;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        border-radius: 50%;
        background-color: ${({ selected }) => selected ? 'white' : 'black'};
        * {
                color: ${({ selected }) => selected ? 'black' : 'white'};
        }
        cursor: pointer;
        &:hover {
                background-color: white;
        }
        &:hover > * {
                color: ${({ selected }) => selected ? 'white' : 'black'};
                color: black;
        }
`;

export const CategoryName = styled.h5`
        font-size: 20px;
        letter-spacing: 3px;
        font-weight: bold;
        max-width: 120px;
        text-align: center;
`;

export const CategorySymbol = styled.i`
        font-size: 40px;
        line-height: 40px;
`;

export const CategoryCardsContainer = styled.div`
        margin: 0 auto;
        width: 100%;
        display: grid;
        grid-template-columns: repeat(auto-fit, 200px);
        grid-gap: 20px;
        justify-content: center;
        align-items: center;
        padding: 50px 0;
`;

