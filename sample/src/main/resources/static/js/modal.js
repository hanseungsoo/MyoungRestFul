function clickAllFriendModal() {
	$('#AllfriendModalCenter').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget);
		  var modal = $(this);
		  modal.find('.modal-title').text('친구 추가');
		  modal.find('label.col-form-label').text('친구이름');
		  modal.find('button.btn.btn-primary').text('추가하기');
		  modal.find('button.modalClose').on('click', function() {
			  $('#AllfriendModalCenter').find('input.form-control').val('');
//			  $('#addfriendTable').DataTable().clear().draw();
//	          $('#addfriendTable').DataTable().ajax.json('').load();
		  });
		  
		  modal.find('input.form-control').on('keyup', function() {
			  delay(function(){
				  var names = modal.find('input.form-control').val();
//				  getFriendForGroup('addfriendTable', '', {name: names})
//				  
//				  
//				  getFriendByGroup(_this, 'addfriendTable', {name: names});
				  
				  getFriendForGroup('addfriendTable', {name: names});
				  
			    }, 1000 );
		  });
		  modal.find('button.btn.btn-primary').on('click', function(_this) {
			  
			  addFriend(_this);
			  $('#AllfriendModalCenter').modal('hide');
			  
		  });
	});
}

function clickGroupFriendModal(gSeq, mainTableId) {
	console.log(gSeq);
	$('#friendGroupModalCenter').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget);
		  var modal = $(this);
		  modal.find('.modal-title').text('그룹에 친구등록');
		  modal.find('label.col-form-label').text('친구이름');
		  modal.find('button.btn.btn-primary').text('추가하기');
		  modal.find('button.modalClose').on('click', function() {
			  $('#friendGroupModalCenter').find('input.form-control').val('');
//			  $('#friendGroupTable').DataTable().clear().draw();
		  });
		  
		  modal.find('input.form-control').on('keyup', function() {
			  delay(function(){
				  var names = modal.find('input.form-control').val();
				  getFriendForGroup('friendGroupTable', {name: names});
			    }, 1000 );
		  });
		  modal.find('button.btn.btn-primary').on('click', function(_this) {
			  addFriendGroup('friendGroupTable', gSeq, mainTableId);
			  $('#friendGroupModalCenter').modal('hide');
		  });

	})
}


function clickModal() {
	$('#bookModalCenter').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget);
		  var flag = button.data('whatever');

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
					  postGroupPannel(groupName);
				  }
			  })
		  } 
	})
}

function dupChecker() {
	$('#dupModalCenter').on('show.bs.modal', function (event) {
		
		  var button = $(event.relatedTarget);
		  var flag = button.data('whatever');

		  var modal = $(this);
		  if(flag == 'nameChecker') {
			  modal.find('.modal-title').text('이름 중복 검사');
			  checkerTable('dupCheckTable', 'http://127.0.0.1:8080/friend/duplname', 'GET')
		  } else {
			  modal.find('.modal-title').text('핸드폰 중복 검사');
			  checkerTable('dupCheckTable', 'http://127.0.0.1:8080/friend/duplphone' ,'GET')
		  }
	})
}

function checkerTable(dataTableId, urls, types) {
	if($('#' + dataTableId).attr('class').indexOf('tableDraw') == -1) {
		var table = $('#' + dataTableId).DataTable({
			'columnDefs': [
		            {
		             "targets": 0,
		             "data": "duplColumn",
		             "orderable" : true,
		            "searchable": true,
		            },
		             {
		             "targets": 1,
		             "data": "duplCnt",
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
		$('#' + dataTableId).addClass('tableDraw');
	} else {
		$.ajax({
	        url : urls,
	        crossOrigin: true,
	        type: types,
	        beforeSend : function(xhr){
	            xhr.setRequestHeader('X-AUTH-TOKEN', $.cookie('token'));
	        },
	        success : function(result) {
	        	$('#' + dataTableId).DataTable().clear();
	        	$('#' + dataTableId).DataTable().ajax.url(urls).load();
	        	
	        }
	    });
	}
}

