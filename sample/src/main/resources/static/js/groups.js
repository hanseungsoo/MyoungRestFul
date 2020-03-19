function initGroupPannel() {
	addGroupPannel('All', '전체', '친구 추가', '친구 삭제', 'bg-info','clickAllFriendModal()', 'deleteAllFriends()', 'friendAdd', 'AllfriendModalCenter');
	
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
        		addGroupPannel(result.data[i].gseq, result.data[i].name, '등록', '제거', 'bg-warning', 'clickGroupFriendModal('+ result.data[i].gseq + ', dataTable' + result.data[i].gseq + ')', 'deleteFriendsByGroup(this)', 'groupFriendAdd', 'friendGroupModalCenter');
        	}
        }
    });
}


function postGroupPannel(groupName) {
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
    		        	addGroupPannel(msg.data.gseq, msg.data.name, '등록', '제거', 'bg-warning', 'clickFriendGroupModal()', 'deleteFriendsByGroup()', 'groupFriendAdd', 'friendGroupModalCenter');

    		        	alert('그룹 추가에 성공하였습니다.');
    		        	$('#bookModalCenter').modal('hide');
    		        }
    		    });
	    	} else {
	    		alert('그룹 추가에 실패하였습니다.');
	    	}
	    }
	});
}




function addGroupPannel(seq, name, svc1, svc2, bgcolor, handler1, handler2, addType, modalType) {
	$.ajax({
        url : './page/accordion.html',
        crossOrigin: true,
        success : function(result) {
        	var tmpSeq = result.replace(/{SEQ}/gi, seq);
        	var tmpName = tmpSeq.replace(/{NAME}/gi, name);
        	var tmpSvc1 = tmpName.replace(/{SVC_1}/gi, svc1);
        	var tmpSvc2 = tmpSvc1.replace(/{SVC_2}/gi, svc2);
        	var tmphandler1 = tmpSvc2.replace(/{HANDLER_1}/gi, handler1);
        	var tmphandler2 = tmphandler1.replace(/{HANDLER_2}/gi, handler2);
        	var tmpAddType = tmphandler2.replace(/{ADD_TYPE}/gi, addType);
        	var tmpModaltype = tmpAddType.replace(/{MODAL_TYPE}/gi, modalType);
        	
        	
        	var tmp = tmpModaltype.replace(/{BG_COLOR}/gi, bgcolor);
        	
        	$('#accordionMain').append(tmp);
        }
    });
}



function getFriendForGroup(tableId, args) {
	var urls;
	var types;
	if(tableId == 'dataTableAll') {
		urls = 'http://127.0.0.1:8080/friend/';
		types = 'GET'
	} else if(tableId == 'addfriendTable'){
		urls = 'http://127.0.0.1:8080/user/notfriend/' + args.name;
		types = 'GET'
	} else if(tableId == 'friendGroupTable'){
		urls = 'http://127.0.0.1:8080/friend/name/' + args.name;
		types = 'GET'
	} else {
		urls = 'http://127.0.0.1:8080/friend/' + args;
		types = 'GET'
	}
	
	getFriendForGroupPannel(tableId, urls, types);
	
}


