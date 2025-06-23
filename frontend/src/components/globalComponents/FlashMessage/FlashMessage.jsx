import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { MessageBox, OKButton, Overlay } from "./FlashMessageStyles.js";

const FlashMessage = ({ message, type = 'info', onClose, duration = 0, fromLogin = false, redirect = null }) => {
    const [countdown, setCountdown] = useState(duration / 1000);
    const navigate = useNavigate();

    let handleOkButton;
    if (redirect) {
        handleOkButton = () => navigate(redirect);
    // } else if (fromLogin) {
    } else {
        handleOkButton = onClose;
    }

    useEffect(() => {
        if (duration > 0) {
            const interval = setInterval(() => {
                setCountdown(prev => {
                    if (prev <= 1) {
                        clearInterval(interval);
                        return 0;
                    }
                    return prev - 1;
                });
            }, 1000);

            const timeout = setTimeout(() => {
                onClose();
            }, duration);

            return () => {
                clearInterval(interval);
                clearTimeout(timeout);
            };
        }
    }, [duration, onClose]);

    if (!message) return null;

    return (
        <Overlay>
            <MessageBox type={type}>
                <p>
                    {message}
                    {fromLogin && countdown > 0 && (
                        <> — Redirigiendo a Home en {countdown}s</>
                    )}
                </p>
                {countdown === 0 && <OKButton onClick={handleOkButton}>OK</OKButton>}
            </MessageBox>
        </Overlay>
    );
};

export default FlashMessage;
