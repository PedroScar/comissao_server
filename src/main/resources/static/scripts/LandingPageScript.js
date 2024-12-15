document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("btn-vantagens").addEventListener("click", function() {
        const targetElement = document.getElementById("section_vantagens");
        const headerHeight = document.getElementById("header").offsetHeight;
        const targetPosition = targetElement.getBoundingClientRect().top + window.scrollY - headerHeight - 120;

        window.scrollTo({
            top: targetPosition,
            behavior: "smooth"
        });
    });

    document.getElementById("btn-porque").addEventListener("click", function() {
        const targetElement = document.getElementById("section_porque");
        const headerHeight = document.getElementById("header").offsetHeight;
        const targetPosition = targetElement.getBoundingClientRect().top + window.scrollY - headerHeight - 120;

        window.scrollTo({
            top: targetPosition,
            behavior: "smooth"
        });
    });
});