package io.myoung.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.myoung.sample.dao.UserDao;
import io.myoung.sample.exception.UserException;
import io.myoung.sample.model.UserItem;
import io.myoung.sample.util.AesUtil;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private AesUtil aesUtil;

	@Transactional
	public Integer insertUserService(UserItem item) throws Exception {
		item.setPassword(aesUtil.encAES(item.getPassword()));
		
		int count = userDao.insertUserDao(item);
		
		if(count == 0)
			throw new UserException("유저 생성에 실패하였습니다.");
		return count;
	}
	
	public UserItem selectUserService(int uSeq) {
		
		return userDao.selectUserDao(uSeq);
	}
	
}
