export function call(api, method, request) {
    let options = {
        headers: new Headers({
            "Content-Type": "application/hal+json",
        }),
        url: "/api" + api,
        method: method,
    };

    if (request) {
        options.body = JSON.stringify(request);
    }

    return fetch(options.url, options).then((response) =>
        response.json().then((json) => {
            if (!response.ok) {
                return Promise.reject(json);
            }
            return json;
        })
    );
}