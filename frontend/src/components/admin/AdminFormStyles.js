import styled from 'styled-components'
import { Formik as FormikContainer, Form as FormikForm } from 'formik';

export const AdminWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-bottom: 200px;
    background-color: var(--verdecito);
    
`;

export const ResponseBox = styled.pre`
        margin-top: 50px;
        background: #222;
        color: #0f0;
        padding: 20px;
        border-radius: 5px;
        font-size: 20px;
        
        width: 60%;
        white-space: pre-wrap;
        word-wrap: break-word;
        
        overflow-y: auto;
        max-height: 70vh;

        position: relative;
        margin-left: auto;
        margin-right: auto;
    
        font-family: monospace;
`;

export const SelectWrapper = styled.div`
  display: flex;
  flex-direction; column;
  gap: 50px;
  margin-bottom: 1rem;

  label {
        display: flex;
        flex-direction: row;
        align-items: center;
        font-size: 20px;
        gap: 50px;
        font-family: 'Bebas Neue', sans-serif;
  }

  select {
    padding: 20px;
    border: 2px solid #000;
    border-radius: 10px;
    font-size: 20px;
    font-family: 'Bebas Neue', sans-serif;
  }
`;

export const AdminSubmit = styled.button`
        cursor: pointer;
        border: 2px solid black;
        padding: 1rem 3rem;
        border-radius: 20px;
        font-size: 15px;
        font-family: 'Bebas Neue', sans-serif;
        text-transform: uppercase;
        text-align: center;
        background: var(--oldgold);
        color: black;
        &:disabled {
                background: gray;
        }
        @media screen and (min-width: 768px) {
                font-size: 30px;
        }
`;

export const Formik = styled(FormikContainer)`
    display: flex;
    flex-wrap: wrap;
    flex-direction: column;
    align-items: center;
    padding-bottom: 20px;
`;

export const Form = styled(FormikForm)`
        display: flex;
        margin-top: 50px;
        margin-bottom: 50px;
        flex-direction: column;
        justify-content: space-between;
        align-items: center;
        padding: 100px;
        gap: 30px;
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
                        gap: 25px;
                }
        }
`;