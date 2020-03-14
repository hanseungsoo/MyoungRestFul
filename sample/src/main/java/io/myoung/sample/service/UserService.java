package io.myoung.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.myoung.sample.dao.AddressBookDao;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	
}
