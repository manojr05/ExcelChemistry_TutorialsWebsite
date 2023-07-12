/**
 * 
 */

document.addEventListener("DOMContentLoaded", function() {
    var typed = new Typed("#typing", {
        strings: [" Made Easy !", "Made Simplified !", "Made Understandable !"],
        typeSpeed: 100,
        backSpeed: 100,
        startDelay: 2000,
        loop: true
    });
});

$(document).ready(function(){
    var animationTriggered = false;
    
    $(window).scroll(function() {
        if (!animationTriggered) {
            var scrollTop = $(window).scrollTop();
            var counterTop = $(".counter").offset().top;
            var windowHeight = $(window).height();
            
            if (scrollTop > counterTop - windowHeight) {
                $(".counter").counterUp({
                    delay: 10,
                    time: 1300
                });
                
                animationTriggered = true;
                $(window).off("scroll"); // Unbind the scroll event
            }
        }
    });
});

const swiper = new Swiper('.mySwiper', {
    loop: true,
    speed: 2000,
    slidesPerView: 3,
    speed: 5000,
    spaceBetween: 70,  
    loopFillGroupWithBlank: true,    
    autoplay: {
        delay: 1000,
    },
    pagination: {
    el: '.swiper-pagination',
    clickable: true,
    },
})