package io.myoung.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.myoung.sample.dao.HistoryDao;
import io.myoung.sample.dao.UserDao;
import io.myoung.sample.exception.EncryptException;
import io.myoung.sample.exception.UserException;
import io.myoung.sample.model.HistoryItem;
import io.myoung.sample.model.UserItem;
import io.myoung.sample.util.AesUtil;

/**
 * UserService.java
 * @클래스설명 : 사용자를 위한 서비스 클래스
 */
@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private HistoryDao historyDao;
	@Autowired
	private AesUtil aesUtil;

	/**
	 * @메소드설명 : 사용자 정보를 받아서 회원 가입 처리를 한다.
	 * @param item : 사용자 정보
	 * @return : 정상 등록이면 1, 오류면 오류 응답 발송
	 * @throws EncryptException : 패스워드 암호화 관련 에러 발생
	 */
	@Transactional
	public Integer insertUserService(UserItem item) {
		item.setPassword(aesUtil.encAES(item.getPassword()));
		item.setRole("USER");
		int count = userDao.insertUserDao(item);
		
		if(count == 0)
			throw new UserException("유저 생성에 실패하였습니다.");
		else if(count == 1) {
			int uSeq = userDao.selectUserByEmailDao(item.getEmail()).getUSeq();
			historyDao.insertHistory(HistoryItem.builder().flag("UC").uSeq(uSeq).build());
		}
			
		return count;
	}
	
	/**
	 * @메소드설명 : 사용자 키로 사용자 정보를 가져 온다.
	 * @param uSeq : 사용자 키
	 * @return : 사용자 정보
	 */
	public UserItem selectUserByUserSeqService(int uSeq) {
		
		return userDao.selectUserByUserSeqDao(uSeq);
	}
	
	/**
	 * @메소드설명 : 유저 이름으로 유저 정보를 조회 한다.
	 * @param uName : 유저 이름
	 * @return : 이름으로 검색된 유저 정보 리스트
	 */
	public List<UserItem> selectUserByUnameService(String uName) {
		
		return userDao.selectUserByUnameDao(uName);
	}
	
}
