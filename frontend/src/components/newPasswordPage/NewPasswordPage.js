import styled from "styled-components";
import React from "react";

export const NewPasswordBG = styled.div`
  background: linear-gradient(0deg, var(--marroncito) 0%, var(--tomato) 100%);
  padding: 200px 20px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  font-size: 25px;
`;

export const NewPasswordTitle = styled.h2`
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
    line-height: 60px;
    margin: 0;
  }
`;

export const NewPasswordText = styled.p`
  text-align: center;
  font-family: 'Bebas Neue', sans-serif;
  color: black;
  padding: 10px;

  @media screen and (max-width: 768px) {
    font-size: 25px;
    line-height: 30px;
  }
`;

export const NewPasswordButton = styled.button`
  border: 2px solid black;
  padding: 1rem 3rem;
  border-radius: 30px;
  font-size: 15px;
  font-family: 'Bebas Neue', sans-serif;
  text-transform: uppercase;
  background: var(--amarillo);
  color: black;
  cursor: pointer;
`;

export const NewSubmitPasswordButton = styled.button`
  border: 2px solid black;
  margin-bottom: 5px;
  padding: 1rem 3rem;
  border-radius: 30px;
  font-size: 15px;
  font-family: 'Bebas Neue', sans-serif;
  text-transform: uppercase;
  background: var(--amarillo);
  color: black;
  cursor: pointer;
`;


export const NewPasswordInput =  styled.input.attrs(props => ({
  type: props.type || 'password', // Asegura que sea tipo password por defecto, o el tipo que le pases
}))`
  background-color: white;
  border: ${({ erroneous }) => (erroneous ? '3px solid #fb103d' : 'none')};
  outline: none;
  padding: 20px;
  border-radius: 15px;
  font-size: 20px;
  font-family: 'Bebas Neue', sans-serif;
  ::placeholder {
    opacity: 50%;
  }
  :-webkit-autofill,
  :-webkit-autofill:hover,
  :-webkit-autofill:focus {
    -webkit-box-shadow: 0 0 0 10px white inset; /* Cambié var(--gray-bg) a white si el background es blanco */
    -webkit-text-fill-color: gray; /* Mantenemos el color de texto para autofill */
  }
  
  @media screen and (min-width: 768px) {
    width: 500px;
  }
`;