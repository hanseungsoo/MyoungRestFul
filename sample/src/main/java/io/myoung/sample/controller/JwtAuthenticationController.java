package io.myoung.sample.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.myoung.sample.controller.request.JwtRequest;
import io.myoung.sample.controller.response.HttpSuccessResponse;
import io.myoung.sample.exception.UserLoginException;
import io.myoung.sample.model.JwtTokenItem;
import io.myoung.sample.security.JwtTokenProvider;
import io.myoung.sample.util.AesUtil;

@RestController
@RequestMapping(value="/auth")
public class JwtAuthenticationController {
	
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private AesUtil aesUtil;
	
	@RequestMapping(method = RequestMethod.POST)
	public HttpSuccessResponse<JwtTokenItem> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		UserDetails item = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		
		if (!authenticationRequest.getPassword().equals(aesUtil.decAES(item.getPassword())))
            throw new UserLoginException("패스워드가 틀렸습니다.");
		
		return HttpSuccessResponse.<JwtTokenItem>builder().data(new JwtTokenItem(jwtTokenProvider.createToken(item))).build();
	}
	
	
}
