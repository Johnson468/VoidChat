$(document).ready(function(){
    $(".dropdown1").hover(            
        function() {
            $('.dropdown-menu', this).not('.in .dropdown-menu').stop(true,true).slideDown("400");
            $(this).toggleClass('open');        
        },
        function() {
            $('.dropdown-menu', this).not('.in .dropdown-menu').stop(true,true).slideUp("400");
            $(this).toggleClass('open');       
        }
    );
});
function goHome() {
	document.getElementById('gohome').submit();
}
function goServices() {
	document.getElementById('services').submit();
}
function goAbout() {
	document.getElementById('about').submit();
}
function goNews() {
	document.getElementById('news').submit();
}