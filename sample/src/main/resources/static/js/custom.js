
$(document).ready(function() {
	$('a.logIn').trigger('click');
});

var delay = (function(){
	  var timer = 0;
	  return function(callback, ms){
	    clearTimeout (timer);
	    timer = setTimeout(callback, ms);
	  };
	})();

function doLoginAction(_this) {
	$.ajax({
		url: 'http://127.0.0.1:8080/auth',
	    type: 'POST',
	    data: getFormData($('#loginForm')),
	    dataType: 'json',
	    contentType: 'application/json; charset=utf-8',
        success : function(msg) {
        	console.log(msg)
        	
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


function getFriendByGroup(_this, dataTableId, args) {
	if(dataTableId == 'dataTableAll') {
		urls = 'http://127.0.0.1:8080/user';
		types = 'GET'
	} else if(dataTableId == 'addfriendTable') {
		urls = 'http://127.0.0.1:8080/user/info/' + args.name;
		types = 'GET'
	}
	
	if($(_this).attr('class').indexOf('tableDraw') == -1) {		
		
		var table = $('#' + dataTableId).DataTable({
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
		             "data": "email",        // this column becomes the first visible one ... (and no)
		             "orderable" : true,
		            "searchable": true,
		            },
		             {
		             "targets": 2,
		             "data": "name",
		             "orderable" : true,
		            "searchable": true,
		             },
		            {
		            "targets": 3,
		            "data": "phone",
		            "orderable" : true,
		            "searchable": true,
		            }
			  ],
			'processing': true,
			'lengthChange': false,
			'searching': false,
			'info': false,
			'ajax': {
				url: urls,
				type: types,
				beforeSend : function(xhr){
		            xhr.setRequestHeader('X-AUTH-TOKEN', $.cookie('token'));
		        }
			},
			'createdRow': function(row, data, dataIndex) {
				$(row).addClass('bg-dark');
			}
		});
		
		table
		.order([1, 'desc'])
		.draw();
		$(_this).addClass('tableDraw');
	} else {
		if(dataTableId == 'dataTableAll') {
			
		} else if(dataTableId == 'addfriendTable') {
			
			$.ajax({
		        url : urls,
		        crossOrigin: true,
		        type: types,
		        beforeSend : function(xhr){
		            xhr.setRequestHeader('X-AUTH-TOKEN', $.cookie('token'));
		        },
		        success : function(result) {
		        	console.log(result)
		        	$('#addfriendTable').DataTable().clear();
		        	$('#addfriendTable').DataTable().ajax.url(urls).load();
		        	
		        }
		    });
		}
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
            addGroupAll('All', '전체', '친구 추가', '친구 삭제', 'bg-info','clickFriendModal(this)');
            selectGroup();
        }
    });
};

function addGroupAll(seq, name, svc1, svc2, bgcolor, handler) {
	$.ajax({
        url : './page/accordion.html',
        crossOrigin: true,
        success : function(result) {
        	var tmpSeq = result.replace(/{SEQ}/gi, seq);
        	var tmpName = tmpSeq.replace(/{NAME}/gi, name);
        	var tmpSvc1 = tmpName.replace(/{SVC_1}/gi, svc1);
        	var tmpSvc2 = tmpSvc1.replace(/{SVC_2}/gi, svc2);
        	var tmphandler1 = tmpSvc2.replace(/{HANDLER_1}/gi, handler);
        	var tmp = tmphandler1.replace(/{BG_COLOR}/gi, bgcolor);
        	
        	$('#accordionMain').append(tmp);
        }
    });
}

function selectGroup() {
	$.ajax({
        url : 'http://127.0.0.1:8080/group',
        crossOrigin: true,
        type: 'GET',
        beforeSend : function(xhr){
            xhr.setRequestHeader('X-AUTH-TOKEN', $.cookie('token'));
        },
        success : function(result) {
        	console.log(result)
        	for (i = 0; i < result.data.length; i++) {
        		
        		addGroupAll(result.data[i].gseq, result.data[i].name, '등록', '제거', 'bg-warning', 'clickFriendModal()');
        	}
        }
    });
}

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
	    beforeSend : function(xhr){
            xhr.setRequestHeader('X-AUTH-TOKEN', $.cookie('token'));
        },
	    contentType: 'application/json; charset=utf-8',
	    success: function(msg){
	    	console.log(msg)
	    	if(msg.status == 'SUCCESS') {
	    		$.ajax({
    		        url : './page/accordion.html',
    		        crossOrigin: true,
    		        success : function(result) {
    		        	var tmpSeq = result.replace(/{SEQ}/gi, msg.data.gseq);
    		        	var tmpName = tmpSeq.replace(/{NAME}/gi, msg.data.name);
    		        	var tmpSvc1 = tmpName.replace(/{SVC_1}/gi, '등록');
    		        	var tmpSvc2 = tmpSvc1.replace(/{SVC_2}/gi, '제거');
    		        	var tmp = tmpSvc2.replace(/{BG_COLOR}/gi, 'bg-warning');
    		        	
    		        	$('#accordionMain').append(tmp);
    		        	alert('그룹 추가에 성공하였습니다.');
    		        	$('#bookModalCenter').modal('hide');
    		        }
    		    });
	    	} else {
	    		alert('그룹 추가에 실패하였습니다.1');
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
			  modal.find('.modal-title').text('그룹 추가');
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


function clickFriendModal(_this) {
	$('#friendModalCenter').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  var flag = button.data('whatever') // Extract info from data-* attributes
		  
		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		  var modal = $(this);
		  if(flag == 'friendAdd') {
			  modal.find('.modal-title').text('친구 추가');
			  modal.find('label.col-form-label').text('친구이름');
			  modal.find('button.btn.btn-primary').text('추가하기');
			  modal.find('button.modalClose').on('click', function() {
				  $('#friendModalCenter').find('input.form-control').val('');
				  $('#addfriendTable').DataTable().clear().draw();
			  });
			  
			  modal.find('input.form-control').on('keyup', function() {
				  
				  delay(function(){
				      
					  var names = modal.find('input.form-control').val();
					  
					  getFriendByGroup(_this, 'addfriendTable', {name: names});
					  
				    }, 1000 );
				  
				  
				  
			  });
			  modal.find('button.btn.btn-primary').on('click', function() {
				  
			  });
		  } 
	})
}

