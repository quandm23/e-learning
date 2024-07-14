/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


const buttonshow = document.querySelector("#show");
const buttonclose = document.querySelector("#close");
const menu = document.querySelector("#menu");
if (buttonshow && buttonclose && menu) {
    buttonshow.onclick = function () {
        try {
            menu.classList.add("open");
            buttonshow.classList.add("close");
        } catch (error) {
            console.error("Error in buttonshow.onclick:", error);
        }
    }

    buttonclose.onclick = function () {
        try {
            menu.classList.remove("open");
            buttonshow.classList.remove("close");
        } catch (error) {
            console.error("Error in buttonclose.onclick:", error);
        }
    }
}


