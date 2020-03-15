package io.myoung.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.myoung.sample.controller.response.HttpSuccessResponse;
import io.myoung.sample.model.UserItem;
import io.myoung.sample.service.FriendService;

@RestController
@RequestMapping(value="/friend", produces = {"application/json"})
public class FriendController {
	
	@Autowired
	private FriendService friendService;
	
	@RequestMapping(method=RequestMethod.GET, value="/{G_SEQ}")
	public HttpSuccessResponse<List<UserItem>> getFriendByUserSeqAndGroupSeq(@PathVariable(value="G_SEQ") int gSeq) {
		return HttpSuccessResponse.<List<UserItem>>builder().data(friendService.selectFriendByGroupSeqService(gSeq)).build();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/{G_SEQ}/{F_SEQ}")
	public HttpSuccessResponse<Integer> insertFriendByFriendSeq(@PathVariable(value="F_SEQ") int fSeq,
																		@PathVariable(value="G_SEQ") int gSeq) {
		return HttpSuccessResponse.<Integer>builder().data(friendService.insertFriendByFriendSeqService(fSeq, gSeq)).build();
	}
//	@RequestMapping(method=RequestMethod.POST, value="/{F_SEQ}/{G_SEQ}")
//	public HttpSuccessResponse<List<FriendItem>> insertFriendByFriendSeq@PathVariable(value="G_SEQ") int gSeq) {
//		return HttpSuccessResponse.<List<FriendItem>>builder().data(friendService.selectFriendByGroupSeqService(gSeq)).build();
//	}
}
