package io.myoung.sample.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.myoung.sample.dao.UserLoginDao;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserLoginDao userloginDao;

	@Override
	public UserDetails loadUserByUsername(String eMail) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return userloginDao.selectLoginByUserId(eMail);
	}

}
