package io.myoung.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.myoung.sample.controller.response.HttpSuccessResponse;
import io.myoung.sample.model.GroupItem;
import io.myoung.sample.service.GroupService;

/**
 * GroupController.java
 * @클래스설명 : 그룹과 관련된 컨트롤러 클래스 
 */
@RestController
@RequestMapping(value="/group")
public class GroupController {
	
	@Autowired
	private GroupService groupService;

	/**
	 * @메소드설명 : 사용자 키로 사용자가 가진 그룹 리스트를 가져 온다.
	 * @param uSeq : 사용자 키
	 * @return : 그룹 리스트
	 */
	@RequestMapping(method=RequestMethod.GET, value="/{U_SEQ}")
	public HttpSuccessResponse<List<GroupItem>> getGroupListbyUserSeq(@PathVariable(value="U_SEQ") int uSeq) {
		
		return HttpSuccessResponse.<List<GroupItem>>builder().data(groupService.selectGroupListByUserSeqService(uSeq)).build();
	}
	
	/**
	 * @메소드설명 : 새로운 그룹을 등록 한다.
	 * @param uSeq : 사용자 키
	 * @param name : 새로운 그룹 명
	 * @return : 정상 등록이면 1, 오류면 오류 응답 발송
	 */
	@RequestMapping(method=RequestMethod.POST, value="/{U_SEQ}/{NAME}")
	public HttpSuccessResponse<Integer> insertGroupbyUserSeq(@PathVariable(value="U_SEQ") int uSeq,
															@PathVariable(value="NAME") String name) {
		
		return HttpSuccessResponse.<Integer>builder().data(groupService.insertGroupByUserSeqService(uSeq, name)).build();
	}
	
}
