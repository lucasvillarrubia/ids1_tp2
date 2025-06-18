export const apiRequest = async (url, method = 'GET', body = null) => {
    const headers = {
        'Content-Type': 'application/json',
    };

    if (body && !(body instanceof FormData)) {
        body = JSON.stringify(body);
    }

    const response = await fetch(url, {
        method,
        headers,
        body,
    });

    if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
    }

    return response;
}