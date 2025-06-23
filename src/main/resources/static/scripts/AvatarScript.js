function handleImageError(imgElement, errorMessage) {
    const span = document.createElement("span");
    span.textContent = errorMessage;
    span.className =
    "inline-block flex items-center justify-center text-center";
    imgElement.parentNode.replaceChild(span, imgElement);
}