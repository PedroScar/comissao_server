function exibirPopup() {
    // Exibe o popup e o overlay
    const popupOverlay = document.getElementById('popup-overlay');
    if (popupOverlay) {
        popupOverlay.classList.remove('opacity-0');
        popupOverlay.classList.remove('pointer-events-none');
    }

    // Adiciona listeners para fechar o popup
    adicionarListenersParaFecharPopup();
}

function fecharPopup() {
    const popupOverlay = document.getElementById('popup-overlay');
    if (popupOverlay) {
        popupOverlay.classList.add('opacity-0');
        popupOverlay.classList.add('pointer-events-none');
    }
}

function adicionarListenersParaFecharPopup() {
    // Remove listeners antigos para evitar duplicação
    document.removeEventListener('click', handleClosePopupClick);
    document.removeEventListener('keydown', handleEscapeKey);

    // Adiciona novos listeners
    document.addEventListener('click', handleClosePopupClick);
    document.addEventListener('keydown', handleEscapeKey);
}

function handleClosePopupClick(event) {
    // Fecha o popup se o clique foi no botão de fechar ou no overlay
    const closeButton = event.target.closest('#close-popup');
    const popupOverlay = document.getElementById('popup-overlay');

    if (closeButton || event.target === popupOverlay) {
        fecharPopup();
    }
}

function handleEscapeKey(event) {
    // Fecha o popup se a tecla Esc for pressionada
    if (event.key === 'Escape') {
        fecharPopup();
    }
}

// Adiciona listeners iniciais (opcional, se o popup já existir no carregamento da página)
document.addEventListener('DOMContentLoaded', function() {
    adicionarListenersParaFecharPopup();
});