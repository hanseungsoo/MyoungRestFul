package io.myoung.sample.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.myoung.sample.controller.response.HttpSuccessResponse;
import io.myoung.sample.model.UserItem;
import io.myoung.sample.security.JwtTokenProvider;
import io.myoung.sample.service.FriendService;
import io.myoung.sample.value.StatusEnum;


/**
 * FriendController.java
 * @클래스설명 : 주소록과 관련된 컨트롤러 클래스 
 */
@RestController
@RequestMapping(value="/friend", produces = {"application/json"})
public class FriendController {
	
	@Autowired
	private FriendService friendService;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	/**
	 * @메소드설명 : 모든 유저 정보를 가져 온다.
	 * @return : 해당 사용자의 모든 유저 
	 */
	@RequestMapping(method=RequestMethod.GET, value="/")
	public HttpSuccessResponse<List<UserItem>> getAllFriendByUserSeq(@RequestHeader(value="X-AUTH-TOKEN") String token) {
		UserItem item = jwtTokenProvider.getUserItem(token);
		return HttpSuccessResponse.<List<UserItem>>builder().status(StatusEnum.SUCCESS).data(friendService.selectAllFriendService(item.getUSeq())).build();
	}
	
	/**
	 * @메소드설명 : 특정 그룹에 속한 모든 유저 정보를 가져 온다.
	 * @param gSeq : 그룹의 키
	 * @return : 그룹에 속한 모든 유저 
	 */
	@RequestMapping(method=RequestMethod.GET, value="/{G_SEQ}")
	public HttpSuccessResponse<List<UserItem>> getFriendByUserSeqAndGroupSeq(@PathVariable(value="G_SEQ") int gSeq) {
		return HttpSuccessResponse.<List<UserItem>>builder().status(StatusEnum.SUCCESS).data(friendService.selectFriendByGroupSeqService(gSeq)).build();
	}
	
	/**
	 * @메소드설명 : 특정 그룹에 유저를 등록한다. 
	 * 			 단, 그룹이 '전체'그룹일시 주소록에도 등록한다. 
	 * @param fSeq : 주소록에 등록할 유저 키
	 * @param gSeq : 그룹의 키
	 * @return 정상 등록이면 1, 오류면 오류 응답 발송
	 */
	@RequestMapping(method=RequestMethod.POST, value="/{G_SEQ}/{F_SEQ}")
	public HttpSuccessResponse<Integer> insertFriendByFriendSeq(
			@RequestHeader(value="X-AUTH-TOKEN") String token,
			@PathVariable(value="F_SEQ") int fSeq,
			@PathVariable(value="G_SEQ") int gSeq) {
		UserItem item = jwtTokenProvider.getUserItem(token);
		return HttpSuccessResponse.<Integer>builder().status(StatusEnum.SUCCESS).data(friendService.insertFriendByFriendSeqService(fSeq, gSeq,item.getUSeq())).build();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/{F_SEQ}")
	public HttpSuccessResponse<Integer> insertFriendByFriendSeq(
			@RequestHeader(value="X-AUTH-TOKEN") String token,
			@PathVariable(value="F_SEQ") int fSeq) {
		UserItem item = jwtTokenProvider.getUserItem(token);
		return HttpSuccessResponse.<Integer>builder().status(StatusEnum.SUCCESS).data(friendService.insertFriendByFriendSeqService(fSeq,item.getUSeq())).build();
	}
	
//	@RequestMapping(method = RequestMethod.DELETE, value = "/{F_SEQ}")
//	public HttpSuccessResponse<Integer> deleteFriendByFriendSeq(
//			@RequestHeader(value="X-AUTH-TOKEN") String token,
//			@PathVariable(value="F_SEQ") int fSeq){
//		UserItem item = jwtTokenProvider.getUserItem(token);
//		return HttpSuccessResponse.<Integer>builder().status(StatusEnum.SUCCESS).data(friendService.deleteFriendByFriendSeqService(fSeq, item.getUSeq())).build();
//	}
//	
	@RequestMapping(method = RequestMethod.DELETE)
	public HttpSuccessResponse<Integer> deleteFriendsByFSeq(
			@RequestHeader(value="X-AUTH-TOKEN") String token,
			@RequestBody Integer[] fSeq){
		UserItem item = jwtTokenProvider.getUserItem(token);
		return HttpSuccessResponse.<Integer>builder().status(StatusEnum.SUCCESS).data(friendService.deleteFriendByFriendSeqService(fSeq, item.getUSeq())).build();
	}
}
