window.addEventListener('DOMContentLoaded', function() {
    const dropdowns = document.querySelectorAll('[ktDropdown]');
    dropdowns.forEach(function(dropdown) {
        const button = dropdown.querySelector('#dropdownButton');
        const menu = dropdown.querySelector('#dropdownMenu');
        const chevron = dropdown.querySelector('#dropdownChevron');
        button.addEventListener('click', function() {
            chevron
            menu.classList.toggle('hidden');
        });
    });
});