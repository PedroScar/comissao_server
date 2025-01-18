function initializeAutoValidation(form) {
    console.log("LOG: Inicializando validação para o formulário", form);

    const submitButtons = form.querySelectorAll('[ktAutovalidateButton]');
    if (submitButtons.length === 0) {
        console.warn("LOG ERRO: Nenhum botão encontrado no formulário", form);
        return;
    }

    function validateForm() {
        const inputs = Array.from(form.querySelectorAll('input[required], textarea[required], select[required]'));
        const allValid = inputs.every(input => input.value.trim() !== "");
        submitButtons.forEach(button => {
            button.disabled = !allValid;
        });
    }

    form.addEventListener('input', validateForm);
    validateForm(); // Validação inicial
}

function observeForDynamicForms() {
    const observer = new MutationObserver(mutations => {
        mutations.forEach(mutation => {
            mutation.addedNodes.forEach(node => {
                if (node.tagName === 'FORM' && node.hasAttribute('ktAutovalidate')) {
                    initializeAutoValidation(node);
                }
            });
        });
    });

    observer.observe(document.body, { childList: true, subtree: true });
}

document.addEventListener('DOMContentLoaded', () => {
    console.log("LOG: Procurando formulários ao carregar a página");
    document.querySelectorAll('[ktAutovalidate]').forEach(initializeAutoValidation);

    console.log("LOG: Iniciando observador de mudanças no DOM");
    observeForDynamicForms();
});