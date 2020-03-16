/**
 * 
 */

$(document).ready(function() {
	$('#homeMenu').trigger('click');
});

function getSignInPage(_this) {
	$.ajax({
        url : "./page/signIn.html",
        crossOrigin: null,
        success : function(result) {
            $("#main").html(result);
            setMenuActive(_this);
            
            
        }
    });
};

function getHomePage(_this) {
	$.ajax({
        url : "./page/main.html",
        crossOrigin: null,
        success : function(result) {
            $("#main").html(result);
            setMenuActive(_this);
        }
    });
};

function getSignUp(_this) {
	$.ajax({
        url : "./page/signUp.html",
        crossOrigin: null,
        success : function(result) {
            $("#main").html(result);
        }
    });
};

function setMenuActive(_this) {
	$(_this).parent().parent().children().removeClass('active');
	$(_this).parent().addClass('active');
};