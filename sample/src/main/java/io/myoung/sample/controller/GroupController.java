package io.myoung.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.myoung.sample.controller.response.HttpSuccessResponse;
import io.myoung.sample.model.GroupItem;
import io.myoung.sample.service.GroupService;

@RestController
@RequestMapping(value="/group")
public class GroupController {
	
	@Autowired
	private GroupService groupService;

	@RequestMapping(method=RequestMethod.GET, value="/{U_SEQ}")
	public HttpSuccessResponse<List<GroupItem>> getGroupListbyUserSeq(@PathVariable(value="U_SEQ") int uSeq) {
		
		return HttpSuccessResponse.<List<GroupItem>>builder().data(groupService.selectGroupListByUserSeqService(uSeq)).build();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/{U_SEQ}/{NAME}")
	public HttpSuccessResponse<Integer> insertGroupbyUserSeq(@PathVariable(value="U_SEQ") int uSeq,
															@PathVariable(value="NAME") String name) {
		
		return HttpSuccessResponse.<Integer>builder().data(groupService.insertGroupByUserSeqService(uSeq, name)).build();
	}
	
//	@RequestMapping(method=RequestMethod.GET, value="/{U_SEQ}/{G_SEQ}")
//	public HttpResponse<List<GroupItem>> getUserListbyGroupSeq(@PathVariable(value="U_SEQ") int uSeq,
//																@PathVariable(value="G_SEQ") int gSeq) {
//		
//		return HttpResponse.<List<UserItem>>builder().data(groupService.selectUserListbyGroupSeqService(uSeq, uSeq)).build();
//	}
}
