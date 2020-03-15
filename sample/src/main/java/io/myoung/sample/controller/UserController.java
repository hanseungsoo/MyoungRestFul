package io.myoung.sample.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.myoung.sample.controller.response.HttpSuccessResponse;
import io.myoung.sample.model.UserItem;
import io.myoung.sample.service.UserService;

/**
 * UserController.java
 * @클래스설명 : 사용자와 관련된 컨트롤러 클래스 
 */
@RestController
@RequestMapping(value="/user", produces = {"application/json"})
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * @메소드설명 : 사용자 키로 사용자 정보를 가져 온다.
	 * @param uSeq : 사용자 키
	 * @return : 사용자 정보
	 */
	@RequestMapping(method=RequestMethod.GET, value="/{U_SEQ}")
	public HttpSuccessResponse<UserItem> getUser(@PathVariable(value="U_SEQ") int uSeq) {
		return HttpSuccessResponse.<UserItem>builder().data(userService.selectUserByUserSeqService(uSeq)).build();
	}
	
	/**
	 * @메소드설명 : 유저 이름으로 유저 정보를 조회 한다.
	 * @param uName : 유저 이름
	 * @return : 이름으로 검색된 유저 정보 리스트
	 */
	@RequestMapping(method=RequestMethod.GET, value="/name/{U_NAME}")
	public HttpSuccessResponse<List<UserItem>> getUserByUname(@PathVariable(value="U_NAME") String uName) {
		return HttpSuccessResponse.<List<UserItem>>builder().data(userService.selectUserByUnameService(uName)).build();
	}
	
	/**
	 * @메소드설명 : 사용자 정보를 받아서 회원 가입 처리를 한다.
	 * @param item : 사용자 정보
	 * @return : 정상 등록이면 1, 오류면 오류 응답 발송
	 * @throws Exception : 패스워드 암호화 관련 에러 발생
	 */
	@RequestMapping(method=RequestMethod.POST)
	public HttpSuccessResponse<Integer> insertUser(@RequestBody @Valid UserItem item) throws Exception {
		return HttpSuccessResponse.<Integer>builder().data(userService.insertUserService(item)).build();
	}
}
