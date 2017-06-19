import fetch from 'isomorphic-fetch';

export default function (href, options = {}) {
    const { headers: requestHeaders = {} } = options;

    return new Promise((resolve, reject) => {
        fetch(href, {
            credentials: 'same-origin',
            ...options,
            headers: {
                'Content-Type': 'application/json',
                ...requestHeaders
            }
        })
        .then((response) => {
            if (response.ok) {
                if (requestHeaders && requestHeaders.Accept) {
                    resolve(response.json());
                } else {
                    resolve(response);
                }
            } else {
                if (response.status === 403) {
                    document.location.reload();
                    return;
                }
                if (response.headers.get('content-type') === 'application/json') {
                    response.json()
                        .then((errorObj) => {
                            reject(errorObj);
                        })
                        .catch(() => {
                            reject(response);
                        });
                } else {
                    reject(response);
                }
            }
        });
    });
}