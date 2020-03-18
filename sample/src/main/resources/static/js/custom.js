/**
 * 
 */

var table;
$(document).ready(function() {
	$('a.home').trigger('click');
});

function doLoginAction(_this) {
	$.ajax({
		url: 'http://127.0.0.1:8080/auth',
	    type: 'POST',
	    data: getFormData($('#loginForm')),
	    dataType: 'json',
	    contentType: 'application/json; charset=utf-8',
        success : function(msg) {
        	if(msg.status == 'SUCCESS') {
	    		if(msg.data.token != '' || msg.data.token != undefined) {
	    			$.cookie('token', msg.data.token);
	    			getHomePage($('a.home'));
	    			$('a.logIn').text('LogOut');
	    		} else {
	    			alert('로그인에 실패하였습니다.');
	    		}
	    	} else {
	    		alert('로그인에 실패하였습니다.');
	    	}
        }
    });
}

function getFriendByGroup(_this) {
	
	if(table == null) {
		table = $('#example').DataTable({
			"columnDefs": [
			    { "orderable": false, "targets": 0 }
			  ],
			"processing": true,
			'lengthChange': false,
			"searching": false,
			"info": false
			
		});
		
		table
		.order([1, 'desc'])
		.draw();
	} else {
		$($(_this).attr('aria-controls')).css('display', 'none');
	}
}

	
	
	

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
		$('a.logIn').text('LogIn');
		getHomePage($('a.home'));
	}
};

function getHomePage(_this) {
	$.ajax({
        url : './page/main.html',
        crossOrigin: true,
        success : function(result) {
            $('main').html(result);
            setMenuActive(_this);
        }
    });
};

function getBookPage(_this) {
	$.ajax({
        url : './page/book.html',
        crossOrigin: true,
        success : function(result) {
            $('main').html(result);
            setMenuActive(_this);
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

function btnRegister() {
	if(passwordKeyChecker()) {
		$.ajax ({
		    url: 'http://127.0.0.1:8080/user',
		    type: 'POST',
		    data: getFormData($('#registerForm')),
		    dataType: 'json',
		    contentType: 'application/json; charset=utf-8',
		    success: function(msg){
		    	if(msg.status == 'SUCCESS') {
		    		if(msg.data == 1) {
		    			alert('가입에 성공하였습니다.');
		    			getLoginPage($('a.logIn'));
		    		} else {
		    			alert('가입에 실패하였습니다.');
		    		}
		    	} else if(msg.status == 'VALID'){
		    		console.log(msg);
		    		var errorFields = msg.errors; 
		    		for(i=0; i < errorFields.length; i++) {
		    			$('div.' + errorFields[i].field + '.invalid-feedback').text(errorFields[i].reason)
		    		}
		    	}
		    },
		});
	} else {
		alert('패스워드를 확인하세요.');
	}
	
};

function getFormData($form){
    var unindexed_array = $form.serializeArray();
    var indexed_array = {};

    $.map(unindexed_array, function(n, i){
        indexed_array[n['name']] = n['value'];
    });
    
    return JSON.stringify(indexed_array);
}

function passwordKeyChecker(){
	console.log($('input.password').val());
	var pwd1 = $('input.password').val();
	var pwd2 = $('input.passwordAgain').val()
	
	if(pwd1 != '' || pwd2 != '') { 
		if(pwd1 == pwd2) { 
			$('div.passwordAgain.valid-feedback').css('display', 'block');
			$('div.passwordAgain.invalid-feedback').css('display', 'none');
			
			return true;
		} else { 
			$('div.passwordAgain.valid-feedback').css('display', 'none');
			$('div.passwordAgain.invalid-feedback').css('display', 'block');
			
			return false;
		} 
	}
}

function addGroup() {
	$.ajax({
        url : './page/accordion.html',
        crossOrigin: true,
        success : function(result) {
        	var tmp = result.replace(/{SEQ}/gi, 22);
        	$('#accordionMain').append(tmp);
        }
    });
}