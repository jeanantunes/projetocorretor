// Slick //
$(document).on('ready', function() {
			

    $('.single-item').slick({
        lazyLoad: 'ondemand', // ondemand progressive anticipated
        infinite: true,
        dots: true,
        responsive: [{
            breakpoint: 768,
            settings: {
                slidesToShow: 2,
                slidesToScroll: 2,
                infinite: true,
                dots: true
            }
        }, {
            breakpoint: 480,
            settings: {
                autoPlay: true,
                slidesToShow: 1,
                slidesToScroll: 1,
                infinite: true,
                dots: true
            }
        }
        ]
    });
});