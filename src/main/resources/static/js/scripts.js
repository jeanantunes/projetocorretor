// Random background login.html
$(document).ready(function() {
    var images = ['portal.jpg', 'portal_02.jpg', 'portal_03.jpg', 'portal_04.jpg'];
    $('.container-cadastro').css({'background-image': 'url(../img/' + images[Math.floor(Math.random() * images.length)] + ')'});
});