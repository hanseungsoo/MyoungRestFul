function addFriend() {
	var addItems = [];
	
	var trList = $('#addfriendTable tbody').children();
	
	for(var i=0; i < trList.length; i++) {
		var item = trList[i];
		flag = $(item).children('td.select-checkbox').children('input').is(':checked')
		
		if(flag){
			tdList = $(item).children();
			var person = {};
			for(var j=0; j < tdList.length; j++) {
				var tdItem = tdList[j];
				
				if($(tdItem).attr('class') != 'select-checkbox') {
					if(j == 4) {
						addItems.push($(tdItem).text() * 1);
					}
				}
				
			}
		}
	}
	
	if(addItems.length > 0) {
		$.ajax({
			url: 'http://127.0.0.1:8080/friend',
		    type: 'POST',
		    data: JSON.stringify(addItems),
		    dataType: 'json',
		    contentType: 'application/json; charset=utf-8',
		    beforeSend : function(xhr){
	            xhr.setRequestHeader('X-AUTH-TOKEN', $.cookie('token'));
	        },
	        success : function(msg) {
	        	
	        	getFriendForGroup('dataTableAll',{});
	        }
	    });
	}
}





function getFriendForGroupPannel(dataTableId, urls, types) {
	
	if($('#' + dataTableId).attr('class').indexOf('tableDraw') == -1) {
		var table = $('#' + dataTableId).DataTable({
			'columnDefs': [
				{
		            "targets": 0,
		            "data": null,
		            'render': function (data, type, full, meta) {
                        			return '<input type="checkbox" name="rowcheck"  value="' + $('<div/>').text(data).html() + '">';
                    			}
		            },
		            {
		             "targets": 1,
		             "data": "email",
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
		            },
		            {
			            "targets": 4,
			            "data": "useq",
			            "orderable" : true,
			            "searchable": false
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
				$(row).children('td:first-child').addClass('select-checkbox');
				$(row).children('td:last-child').css('display', 'none');
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
		
//		
//		if(dataTableId == 'dataTableAll') {
//			
//		} else if(dataTableId == 'addfriendTable') {
//			$('#addfriendTable').DataTable().clear();
//        	//$('#addfriendTable').DataTable().ajax.url(urls).load();
//			$.ajax({
//		        url : urls,
//		        crossOrigin: true,
//		        type: types,
//		        beforeSend : function(xhr){
//		            xhr.setRequestHeader('X-AUTH-TOKEN', $.cookie('token'));
//		        },
//		        success : function(result) {
//		        	$('#addfriendTable').DataTable().clear();
//		        	$('#addfriendTable').DataTable().ajax.url(urls).load();
//		        	
//		        }
//		    });
//		}
	}
}



function deleteAllFriends() {
	var addItems = [];
	var trList = $('#dataTableAll tbody').children();
	
	for(var i=0; i < trList.length; i++) {
		var item = trList[i];
		flag = $(item).children('td.select-checkbox').children('input').is(':checked')
		
		if(flag) {
			tdList = $(item).children();
			for(var j=0; j < tdList.length; j++) {
				var tdItem = tdList[j];
				
				if($(tdItem).attr('class') != 'select-checkbox') {
					if(j == 4) {
						addItems.push($(tdItem).text());
					}
				}
				
			}
		}
	}
	
	if(addItems.length > 0) {
		$.ajax({
			url: 'http://127.0.0.1:8080/friend',
		    type: 'DELETE',
		    data: JSON.stringify(addItems),
		    dataType: 'json',
		    contentType: 'application/json; charset=utf-8',
		    beforeSend : function(xhr){
	            xhr.setRequestHeader('X-AUTH-TOKEN', $.cookie('token'));
	        },
	        success : function(msg) {
	        	getFriendForGroup('dataTableAll', {});
	        }
	    });
	}	
}

function deleteFriendsByGroup(_this) {
	var addItems = [];
	
	var targetAco = $(_this).parent().parent().parent();
	
	console.log(targetAco)
	
//	var trList = 
//	
//	for(var i=0; i < trList.length; i++) {
//		var item = trList[i];
//		flag = $(item).children('td.select-checkbox').children('input').is(':checked')
//		
//		if(flag) {
//			tdList = $(item).children();
//			for(var j=0; j < tdList.length; j++) {
//				var tdItem = tdList[j];
//				
//				if($(tdItem).attr('class') != 'select-checkbox') {
//					if(j == 4) {
//						addItems.push($(tdItem).text());
//					}
//				}
//				
//			}
//		}
//	}
//	
//	if(addItems.length > 0) {
//		$.ajax({
//			url: 'http://127.0.0.1:8080/friend',
//		    type: 'DELETE',
//		    data: JSON.stringify(addItems),
//		    dataType: 'json',
//		    contentType: 'application/json; charset=utf-8',
//		    beforeSend : function(xhr){
//	            xhr.setRequestHeader('X-AUTH-TOKEN', $.cookie('token'));
//	        },
//	        success : function(msg) {
//	        	getFriendForGroup('dataTableAll', {});
//	        }
//	    });
//	}	
}

function addFriendGroup(tableId, gSeq, mainTable) {
	var addItems = [];
	
	var trList = $('#' + tableId + ' tbody').children();
	
	for(var i=0; i < trList.length; i++) {
		var item = trList[i];
		flag = $(item).children('td.select-checkbox').children('input').is(':checked')
		
		if(flag){
			tdList = $(item).children();
			var person = {};
			for(var j=0; j < tdList.length; j++) {
				var tdItem = tdList[j];
				
				if($(tdItem).attr('class') != 'select-checkbox') {
					if(j == 4) {
						addItems.push($(tdItem).text() * 1);
					}
				}
				
			}
		}
	}
	
	if(addItems.length > 0) {
		$.ajax({
			url: 'http://127.0.0.1:8080/friend/' + gSeq,
		    type: 'POST',
		    data: JSON.stringify(addItems),
		    dataType: 'json',
		    contentType: 'application/json; charset=utf-8',
		    beforeSend : function(xhr){
	            xhr.setRequestHeader('X-AUTH-TOKEN', $.cookie('token'));
	        },
	        success : function(msg) {
	        	getFriendForGroup(mainTable,{});
	        }
	    });
	}
}