import { createGlobalStyle } from "styled-components";

export const GlobalStyles = createGlobalStyle`
        *{
                margin:0;
                padding: 0;
                box-sizing: border-box;
                list-style: none;
                text-decoration: none;
                font-family: 'Raleway', sans-serif;
        }

        html {
                scroll-behavior: smooth;
                scroll-padding-top: 100px;
        }

        :root {
                --marroncito: #db6b30ff;
                --marronzote: #623412ff;
                --rosa: #ee6688ff;
                --amarillo: #ffc907ff;
                --verdecito: #85ff9eff;
                --saucecito: #b0ffc7ff;
                --mentita: #daffefff;

                --vanilla: #FFEEB0ff;
                --rojomunsell: #EC2045ff;
                --tomato: #F26247ff;
                --oldgold: #C7C12Fff;
                --celadon: #A3D39Fff;

                --transparente: rgba(1, 1, 1, 0);
                --borde_texto:  -2px -2px 0 #000, 2px -2px 0 #000, -2px 2px 0 #000, 2px 2px 0 #000;
                --borde_claro: -1px -1px 0 var(--vanilla), 1px -1px 0 var(--vanilla), -1px 1px 0 var(--vanilla), 1px 1px 0 var(--vanilla);
                --borde_fino: -1px -1px 0 var(--marronzote), 1px -1px 0 var(--marronzote), -1px 1px 0 var(--marronzote), 1px 1px 0 var(--marronzote);
        }

        body{
                background-color: var(--marronzote);
        }
`;