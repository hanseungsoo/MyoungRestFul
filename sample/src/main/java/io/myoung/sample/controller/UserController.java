package io.myoung.sample.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.myoung.sample.controller.response.HttpResponse;
import io.myoung.sample.model.GroupItem;
import io.myoung.sample.model.UserItem;
import io.myoung.sample.service.UserService;

@RestController
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET, value="/{U_SEQ}")
	public HttpResponse<UserItem> getUser(@PathVariable(value="U_SEQ") int uSeq) {
		return null;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/")
	public HttpResponse<UserItem> insertUser(@RequestBody @Valid UserItem item) {
		return null;
	}
}
