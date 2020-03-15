package io.myoung.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.myoung.sample.dao.UserDao;
import io.myoung.sample.model.UserItem;
import io.myoung.sample.util.AesUtil;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private AesUtil aesUtil;

	public Integer insertUserService(UserItem item) throws Exception {
		item.setPassword(aesUtil.encAES(item.getPassword()));
		
		return userDao.insertUserDao(item);
	}
	
	public UserItem selectUserService(int uSeq) {
		
		return userDao.selectUserDao(uSeq);
	}
	
}
