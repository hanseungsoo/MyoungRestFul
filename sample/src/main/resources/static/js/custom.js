
$(document).ready(function() {
	$('a.logIn').trigger('click');
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
	    			
	    			if($('a.book') != null) {
	    				$.ajax({
		    		        url : './page/menu.html',
		    		        crossOrigin: true,
		    		        success : function(result) {
		    		        	$('a.active').before(result);
		    		            
		    		            
		    		        }
		    		    });
	    			}
	    			getBookPage($('a.book'));
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
	if($(_this).attr('class').indexOf('tableDraw') == -1) {
		var table = $('#dataTableAll').DataTable({
			'columnDefs': [
				{
		            "targets": 0,
		            "data": null,          // This does the trick... Adding data ...
		            "visible": true,      // And making this columns invisible ...
		            "defaultContent" : '',
		            "orderable" : false,
		            "searchable": false,
		            },
		            {
		             "targets": 1,        // By doing the trick above ...
		             "data": "NAME",        // this column becomes the first visible one ... (and no)
		             "orderable" : false,
		            "searchable": false,
		            },
		             {
		             "targets": 2,
		             "data": "EMAIL",
		             "orderable" : false,
		            "searchable": false,
		             },
		            {
		            "targets": 3,
		            "data": "PHONE",
		            "orderable" : true,
		            "searchable": true,
		            }
			  ],
			'processing': true,
			'lengthChange': false,
			'searching': false,
			'info': false,
			'ajax': {
				url:'./json/rows.json' 
			}
		});
		
//		table.on( 'xhr', function () {
//		    var json = table.ajax.json();
//		    alert( json.data.length +' row(s) were loaded' );
//		} );
		
		$(_this).addClass('tableDraw');
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
		    }
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

function addGroup(groupName) {
	$.ajax ({
	    url: 'http://127.0.0.1:8080/group/' + groupName,
	    type: 'POST',
	    dataType: 'json',
	    contentType: 'application/json; charset=utf-8',
	    success: function(msg){
	    	if(msg.status == 'SUCCESS') {
	    		if(msg.data == 1) {
	    			$.ajax({
	    		        url : './page/accordion.html',
	    		        crossOrigin: true,
	    		        success : function(msg) {
	    		        	console.log(msg);
	    		        	var tmpSeq = result.replace(/{SEQ}/gi, msg.data.gSeq);
	    		        	var tmpName = tmp.replace(/{NAME}/gi, msg.data.name);
	    		        	$('#accordionMain').append(tmp);
	    		        	alert('그룹 추가에 성공하였습니다.');
	    		        	$('#bookModalCenter').modal('hide');
	    		        }
	    		    });
	    		} else {
	    			alert('그룹 추가에 실패하였습니다.');
	    		}
	    	} else {
	    		alert('그룹 추가에 실패하였습니다.');
	    	}
	    }
	});
}

function clickModal(_this) {
	$('#bookModalCenter').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  var flag = button.data('whatever') // Extract info from data-* attributes
		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		  var modal = $(this);
		  if(flag == 'groupAdd') {
			  modal.find('.modal-title').text('그룹추가');
			  modal.find('label.col-form-label').text('그룹명');
			  modal.find('button.btn.btn-primary').text('추가하기');
			  modal.find('button.btn.btn-primary').on('click', function() {
				  var groupName = modal.find('#book-name').val();
				  if(groupName == '' || groupName == undefined) {
					  alert('그룹명을 지정해 주세요.');
				  } else {
					  addGroup(groupName);
				  }
			  })
		  }
	})
}