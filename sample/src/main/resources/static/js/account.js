function doLoginAction() {
	$.ajax({
		url: 'http://127.0.0.1:8080/auth',
	    type: 'POST',
	    data: getFormData($('#loginForm')),
	    dataType: 'json',
	    contentType: 'application/json; charset=utf-8',
        success : function(msg) {
        	
        	if(msg.status == 'SUCCESS') {
	    		if(msg.data.token != '' || msg.data.token != undefined) {
	    			var inFifteenMinutes = new Date(new Date().getTime() + 180 * 60 * 1000);

	    			if($.cookie('token') == null || $.cookie('token') == undefined) {
	    				$.ajax({
		    		        url : './page/menu.html',
		    		        crossOrigin: true,
		    		        success : function(result) {
		    		        	$('a.active').before(result);
		    		        	getBookPage($('a.book'));
		    	    			$('a.logIn').text('LogOut');
		    	    			$.cookie('token', msg.data.token, {
		    	    			    expires: inFifteenMinutes
		    	    			});
		    		        }
		    		    });
	    			}
	    		} else {
	    			alert('로그인에 실패하였습니다.');
	    		}
	    	} else {
	    		alert('로그인에 실패하였습니다.');
	    	}
        }
    });
}



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
		    }
		});
	} else {
		alert('패스워드를 확인하세요.');
	}
	
};



function passwordKeyChecker(){
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

function initHistoryPannel() {
	if($('#historyTable').attr('class').indexOf('tableDraw') == -1) {
		var table = $('#historyTable').DataTable({
			'columnDefs': [
			
		            {
		             "targets": 0,
		             "data": "flag",
		             "orderable" : true,
		            "searchable": true
		            },
		             {
		             "targets": 1,
		             "data": "actionTime",
		             "orderable" : true,
		            "searchable": true
		             },
		            {
		            "targets": 2,
		            "data": "fseq",
		            "orderable" : true,
		            "defaultContent": '',
		            "searchable": true
		            },
		            {
			            "targets": 3,
			            "data": "gseq",
			            "orderable" : true,
			            "defaultContent": '',
			            "searchable": false
			            },
		            {
			            "targets": 4,
			            "data": "hseq",
			            "orderable" : true,
			            "defaultContent": '',
			            "searchable": false
			            }
			  ],
			'processing': true,
			'lengthChange': false,
			'searching': false,
			'info': false,
			'ajax': {
				url: 'http://127.0.0.1:8080/user/history',
				type: 'GET',
				beforeSend : function(xhr){
		            xhr.setRequestHeader('X-AUTH-TOKEN', $.cookie('token'));
		        }
			},
			'createdRow': function(row, data, dataIndex) {
				$(row).addClass('bg-info');
				$(row).children('td:last-child').css('display', 'none');
			}
		});
		
		table
		.order([1, 'desc'])
		.draw();
		$('#historyTable').addClass('tableDraw');
		
	} else {
		$.ajax({
	        url : 'http://127.0.0.1:8080/user/history',
	        crossOrigin: true,
	        type: 'GET',
	        beforeSend : function(xhr){
	            xhr.setRequestHeader('X-AUTH-TOKEN', $.cookie('token'));
	        },
	        success : function(result) {
	        	$('#historyTable').DataTable().clear();
	        	$('#historyTable').DataTable().ajax.url(urls).load();
	        	
	        }
	    });
	}
}