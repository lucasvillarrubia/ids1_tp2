import styled from "styled-components";

export const VerifyEmailBG = styled.div`
  background: linear-gradient(0deg, var(--marroncito) 0%, var(--tomato) 100%);
  padding: 200px 20px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  font-size: 25px;
`;

export const VerifyEmailTitle = styled.h2`
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

export const VerifyEmailText = styled.p`
  text-align: center;
  font-family: 'Bebas Neue', sans-serif;
  color: black;
  padding: 10px;

  @media screen and (max-width: 768px) {
    font-size: 25px;
    line-height: 30px;
  }
`;

export const VerifyEmailButton = styled.button`
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
