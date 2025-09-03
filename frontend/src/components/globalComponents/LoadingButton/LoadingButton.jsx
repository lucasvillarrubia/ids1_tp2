import React, { useState } from 'react';
import { LoadingButtonUI } from './LoadingButtonStyles';

const LoadingButton = ({ isLoading, children }) => {
        const [buttonText, setButtonText] = useState(children);

        const handleButtonClick = async () => {
                if (!isLoading) {
                setButtonText('Loading...');
                await onSubmit();
                setButtonText(children);
                }
        };

        return (
                <LoadingButtonUI className={`loading-button ${isLoading ? 'loading' : ''}`} onClick={handleButtonClick} disabled={isLoading}>
                {buttonText}
                </LoadingButtonUI>
        );
};

export default LoadingButton;
