document.addEventListener("htmx:beforeSwap", (event) => {
    const xhr = event.detail.xhr;

    const parser = new DOMParser();
    const responseHTML = parser.parseFromString(xhr.responseText, "text/html");
    const popupDiv = responseHTML.querySelector("div[lm-popup-nome]");

    const trigger = xhr.getResponseHeader("HX-Trigger");
    if (trigger === "lm-popup-open") {
        exibirPopup();
        return
    } else if(trigger === "lm-popup-close") {
        fecharPopup();
        return
    }
});