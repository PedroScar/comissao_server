package com.pscarpellini.scripts

fun addLandingPageScript(): String {
    return """
                document.addEventListener("DOMContentLoaded", function() {
                    document.getElementById("btn-vantagens").addEventListener("click", function() {
                        const targetElement = document.getElementById("txt-vantagens");
                        const headerHeight = document.querySelector(".landing-header").offsetHeight;
                        const targetPosition = targetElement.getBoundingClientRect().top + window.scrollY - headerHeight;
        
                        window.scrollTo({
                            top: targetPosition,
                            behavior: "smooth"
                        });
                    });
                    
                    document.getElementById("btn-porque").addEventListener("click", function() {
                        const targetElement = document.getElementById("txt-porque");
                        const headerHeight = document.querySelector(".landing-header").offsetHeight;
                        const targetPosition = targetElement.getBoundingClientRect().top + window.scrollY - headerHeight;
        
                        window.scrollTo({
                            top: targetPosition,
                            behavior: "smooth"
                        });
                    });
                });
            """
}