import styled from "styled-components";

export const LoadingButtonUI = styled.button`
        padding: 10px 20px;
        font-size: 16px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
        .loading::after {
                content: '';
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                width: 20px;
                height: 20px;
                border: 4px solid #fff;
                border-top: 4px solid transparent;
                border-radius: 50%;
                animation: spin 1s linear infinite;
        }
        @keyframes spin {
                0% {
                        transform: translate(-50%, -50%) rotate(0deg);
                }
                100% {
                        transform: translate(-50%, -50%) rotate(360deg);
                }
        }
`;