function getLoginPage(_this) {
	if($.cookie('token') == null && $('a.logIn').text() == 'LogIn') {
		$.ajax({
	        url : './page/signIn.html',
	        crossOrigin: true,
	        success : function(result) {
	            $('main').html(result);
	            setMenuActive(_this);
	        }
	    });
	} else {
		$.removeCookie('token');
		$('a.book').remove();
		$('a.history').remove();
		$('a.logIn').text('LogIn');
		getLoginPage($('a.logIn'));
	}
};


function getBookPage(_this) {
	$.ajax({
        url : './page/book.html',
        crossOrigin: true,
        success : function(result) {
            $('main').html(result);
            setMenuActive(_this);
            initGroupPannel();
        }
    });
};


function getHistoryPage(_this) {
	$.ajax({
        url : './page/history.html',
        crossOrigin: true,
        success : function(result) {
            $('main').html(result);
            setMenuActive(_this);
            initHistoryPannel();
        }
    });
};

function getSignUp(_this) {
	$.ajax({
        url : './page/signUp.html',
        crossOrigin: true,
        success : function(result) {
            $('main').html(result);
        }
    });
};

function setMenuActive(_this) {
	$(_this).parent().children().removeClass('active');
	$(_this).addClass('active');
};