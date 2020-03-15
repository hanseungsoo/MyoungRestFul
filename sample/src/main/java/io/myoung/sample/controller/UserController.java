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

@RestController
@RequestMapping(value="/user", produces = {"application/json"})
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET, value="/{U_SEQ}")
	public HttpSuccessResponse<UserItem> getUser(@PathVariable(value="U_SEQ") int uSeq) {
		return HttpSuccessResponse.<UserItem>builder().data(userService.selectUserService(uSeq)).build();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/name/{U_NAME}")
	public HttpSuccessResponse<List<UserItem>> getUserByUname(@PathVariable(value="U_NAME") String uName) {
		return HttpSuccessResponse.<List<UserItem>>builder().data(userService.selectUserByUnameService(uName)).build();
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public HttpSuccessResponse<Integer> insertUser(@RequestBody @Valid UserItem item) throws Exception {
		return HttpSuccessResponse.<Integer>builder().data(userService.insertUserService(item)).build();
	}
}
