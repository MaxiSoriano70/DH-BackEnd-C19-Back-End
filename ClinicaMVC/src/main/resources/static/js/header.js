const menu = document.querySelector(".menu");
const openMenuBtn = document.querySelector(".open-menu-btn");
const closeMenuBtn = document.querySelector(".close-menu-btn");

[openMenuBtn, closeMenuBtn].forEach((btn)=>{
    btn.addEventListener("click",function(){
        menu.classList.toggle("open");
    });
});

menu.querySelectorAll(".contenedor .menu > ul > li.dropdown .fa-caret-down").forEach((down)=>{
    down.addEventListener("click", function(){
        this.closest("li.dropdown").classList.toggle("active");
    });
});