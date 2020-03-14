package io.myoung.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.myoung.sample.controller.response.HttpResponse;
import io.myoung.sample.model.GroupItem;
import io.myoung.sample.model.UserItem;
import io.myoung.sample.service.GroupService;

@RestController
@RequestMapping(value="/group")
public class GroupController {
	
	@Autowired
	private GroupService groupService;

	@RequestMapping(method=RequestMethod.GET, value="/{U_SEQ}")
	public HttpResponse<List<GroupItem>> getGroupListbyUserSeq(@PathVariable(value="U_SEQ") int uSeq) {
		
		return HttpResponse.<List<GroupItem>>builder().data(groupService.selectGroupListByUserSeqService(uSeq)).build();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/")
	public HttpResponse<Integer> insertGroupbyUserSeq(@RequestBody GroupItem item) {
		
		return HttpResponse.<Integer>builder().data(groupService.insertGroupByUserSeqService(item)).build();
	}
	
//	@RequestMapping(method=RequestMethod.GET, value="/{U_SEQ}/{G_SEQ}")
//	public HttpResponse<List<GroupItem>> getUserListbyGroupSeq(@PathVariable(value="U_SEQ") int uSeq,
//																@PathVariable(value="G_SEQ") int gSeq) {
//		
//		return HttpResponse.<List<UserItem>>builder().data(groupService.selectUserListbyGroupSeqService(uSeq, uSeq)).build();
//	}
}
