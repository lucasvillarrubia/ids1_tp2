import React, { useEffect, useState } from 'react';
import { MessageBox, OKButton, Overlay } from "./FlashMessageStyles.js";

const FlashMessage = ({ message, type = 'info', onClose, duration = 0, fromLogin = false }) => {
    const [countdown, setCountdown] = useState(duration / 1000);

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
                        <> — Redirigiendo en {countdown}s</>
                    )}
                </p>
                {!fromLogin && <OKButton onClick={onClose}>OK</OKButton>}
            </MessageBox>
        </Overlay>
    );
};

export default FlashMessage;
