import { styled } from "styled-components";
import { Formik as FormikContainer, Form as FormikForm } from 'formik';

export const Formik = styled(FormikContainer)`
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
`;

export const Form = styled(FormikForm)`
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        align-items: center;
        gap: 30px;
        margin-bottom: 50px;
        width: 100%;
        & div {
                display: flex;
                flex-direction: column;
                flex-wrap: wrap;
                justify-content: center;
                align-items: center;
                width: 100%;
        }
        & a {
                color: black;
                text-align: center;
                margin-top: 20px;
        }
        & a:hover {
                text-decoration: underline;
        }
        @media screen and (min-width: 768px) {
                gap: 20px;
                margin-bottom: 50px;
                & div {
                        gap: 5px;
                }
        }
`;

export const SignupSubmit = styled.button`
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
                font-size: 30px;
        }
`;

export const SignupLabel = styled.label`
        font-size: 20px;
        color: black;
        /* text-shadow: var(--borde_texto); */
        text-align: center;
        width: 100%;
        font-family: 'Bebas Neue', sans-serif;
        font-weight: 700;
        letter-spacing: 1px;
        @media screen and (min-width: 768px) {
                width: 500px;
        }
`;

export const SignupInputStyled = styled.input`
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

export const ErrorMessageUI = styled.p`
  margin: 0;
  margin-top: 5px;
  color: black;
  font-size: 14px;
`;

export const SignupCheckbox = styled.input`
        height: 20px;
`;

export const SignupNewsletter = styled.span`
        font-size: 15px;
        text-shadow: none;
        font-style: italic;
`;




