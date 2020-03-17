/**
 * 
 */

$(document).ready(function() {
	$('a.home').trigger('click');
	
	jQuery.fn.serializeObject = function() {
		var obj = null;
		try {
	
			// this[0].tagName이 form tag일 경우
			if(this[0].tagName && this[0].tagName.toUpperCase() == 'FORM' ) {
				var arr = this.serializeArray();
			
				if(arr){
					obj = {};    
					jQuery.each(arr, function() {
						// obj의 key값은 arr의 name, obj의 value는 value값
						obj[this.name] = this.value;
					});				
				}
			}
		} catch(e) {
			alert(e.message);
		}finally  {}
		return obj;
	};
});

function getSignInPage(_this) {
	$.ajax({
        url : './page/signIn.html',
        crossOrigin: null,
        success : function(result) {
            $('main').html(result);
            setMenuActive(_this);
        }
    });
};

function getHomePage(_this) {
	$.ajax({
        url : './page/main.html',
        crossOrigin: null,
        success : function(result) {
            $('main').html(result);
            setMenuActive(_this);
        }
    });
};

function getSignUp(_this) {
	$.ajax({
        url : './page/signUp.html',
        crossOrigin: null,
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
		    		} else {
		    			alert('가입에 실패하였습니다.');
		    		}
		    		getHomePage($('#homeMenu'));
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