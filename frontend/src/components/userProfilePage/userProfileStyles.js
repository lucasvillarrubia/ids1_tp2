import { styled } from "styled-components";

export const ProfileContainer = styled.div`
        max-width: 600px;
        margin: 80px auto;
        padding: 50px;
        border: 1px solid #623412ff;
        border-radius: 20px;
        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        background: sandybrown;
        color: black;
`;

export const ProfileSection = styled.section`
    margin-bottom: 30px;
    &:last-child {
        margin-bottom: 20px;
    }
`;


export const ProfileTitle = styled.h1`
        font-size: 40px;
        color: black;
        text-align: center;
        font-family: 'Bebas Neue', sans-serif;
        font-weight: 700;
        letter-spacing: 2px;
        margin-bottom: 25px;
        @media screen and (min-width: 768px) {
                width: 500px;
        }
`;


export const  ProfileSubtitle = styled.h2`
        font-size: 20px;
        color: black;
        text-align: center;
        font-family: 'Bebas Neue', sans-serif;
        font-weight: 700;
        letter-spacing: 2px;
        margin-bottom: 25px;
        @media screen and (min-width: 768px) {
        font-size: 35px;
        }
`;
export const ProfileDetailRow = styled.p`
    font-size: 20px;
    font-family: 'Bebas Neue', sans-serif;
    text-transform: uppercase;
    line-height: 1.5;

    strong {
        font-weight: bold;
        color: #333; // Slightly darker for emphasis
    }
`;
export const ActionButton = styled.input`
        background-color: white;
        border: ${({ erroneous }) => (erroneous ? '3px solid #fb103d ' : 'none')};
        outline: none;
        padding: 10px;
        border-radius: 15px;
        font-size: 20px;
        font-family: 'Bebas Neue', sans-serif;
        /* width: 100%; */
        ::placeholder {
                opacity: 60%;
        }
        :-webkit-autofill,
        :-webkit-autofill:hover,
        :-webkit-autofill:focus {
                -webkit-box-shadow: 0 0 0px 1000px var(--gray-bg) inset;
        }
        -webkit-text-fill-color: gray;
        @media screen and (min-width: 768px) {
                width: 500px;
        }
`;
export const ProfileInputField = styled.input`
        background-color: white;
        border: ${({ erroneous }) => (erroneous ? '3px solid #fb103d ' : 'none')};
        outline: none;
        padding: 10px;
        border-radius: 15px;
        font-size: 20px;
        font-family: 'Bebas Neue', sans-serif;
        /* width: 100%; */
        ::placeholder {
                opacity: 60%;
        }
        :-webkit-autofill,
        :-webkit-autofill:hover,
        :-webkit-autofill:focus {
                -webkit-box-shadow: 0 0 0px 1000px var(--gray-bg) inset;
        }
        -webkit-text-fill-color: gray;
        @media screen and (min-width: 768px) {
                width: 500px;
        }
`;
export const PrimaryButton = styled.button`
        cursor: pointer;
        border: 2px solid black;
        padding: 1rem 3rem;
        border-radius: 30px;
        font-size: 15px;
        font-family: 'Bebas Neue', sans-serif;
        text-transform: uppercase;
        text-align: center;
        background: var(--vanilla);
        color: black;
        &:disabled {
                background: gray;
        }
        @media screen and (min-width: 768px) {
                font-size: 25px;
        }
`;
export const SecondaryButton = styled(PrimaryButton)`
    background: #e0e0e0; // A lighter background
    color: #333;

    &:hover {
        background: #ccc;
    }
`;
export const ErrorMessageUI = styled.p`
  margin: 0;
  margin-top: 5px;
  color: black;
  font-size: 14px;
`;
export const SectionSeparator = styled.hr`
    border:2px solid black ;
    border-top: 10px solid ##F26247ff;
    margin: 50px;
`;