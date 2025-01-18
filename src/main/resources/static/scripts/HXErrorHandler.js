document.addEventListener("htmx:beforeSwap", (event) => {
    const xhr = event.detail.xhr;

    const parser = new DOMParser();
    const responseHTML = parser.parseFromString(xhr.responseText, "text/html");
    const toastDiv = responseHTML.querySelector("div[lm-toast-tipo][lm-toast-mensagem]");

    const trigger = xhr.getResponseHeader("HX-Trigger");
    if (trigger === "toast-message") {
        const tipo = toastDiv.getAttribute("lm-toast-tipo");
        const mensagem = toastDiv.getAttribute("lm-toast-mensagem");

        const notyf = NotyfManager.getInstance();
        notyf.open({ type: tipo, message: mensagem });

        event.preventDefault();
        return
    }

    if (toastDiv) {
        const tipo = toastDiv.getAttribute("lm-toast-tipo");
        const mensagem = toastDiv.getAttribute("lm-toast-mensagem");

        const notyf = NotyfManager.getInstance();
        notyf.open({ type: tipo, message: mensagem });
    }
});

document.addEventListener("htmx:afterSwap", (event) => {
    const fragment = event.detail.target;
    const toastDivs = document.querySelectorAll("div[lm-toast-tipo][lm-toast-mensagem]");
    toastDivs.forEach((toastDiv) => {
        toastDiv.remove();
    });
});