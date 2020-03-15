package io.myoung.sample.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.myoung.sample.dao.UserLoginDao;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserLoginDao userloginDao;

	@Override
	public CustomUserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return userloginDao.selectLoginByUserId(id);
	}

}
