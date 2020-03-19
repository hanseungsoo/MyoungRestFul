package io.myoung.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.myoung.sample.dao.GroupDao;
import io.myoung.sample.dao.HistoryDao;
import io.myoung.sample.dao.UserDao;
import io.myoung.sample.exception.EncryptException;
import io.myoung.sample.exception.UserException;
import io.myoung.sample.mapper.UserMapper;
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
	@Autowired
	private GroupDao groupdao;

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
	
	public List<UserItem> selectUserByNotUserSeqService(int uSeq) {
		
		return userDao.selectUserByNotUserSeqDao(uSeq);
	}
	
	/**
	 * @메소드설명 : 유저 이름으로 유저 정보를 조회 한다.
	 * @param uName : 유저 이름
	 * @return : 이름으로 검색된 유저 정보 리스트
	 */
	public List<UserItem> selectUserByUnameService(String uName) {
		
		return userDao.selectUserByUnameDao(uName);
	}
	
	public List<HistoryItem> selectHistoryByUseqService(int uSeq){
		List<HistoryItem> historyList = userDao.selectHistoryByUseqDao(uSeq);

		String fname;

		String gname;

		for(int i=0;i<historyList.size();i++) {

			HistoryItem history = historyList.get(i);

			switch (history.getFlag()) {

			case "UC":

				history.setFlag("유저가 생성되었습니다.");

				break;

			case "UU":

				history.setFlag("유저 정보가 변경되었습니다.");

				break;

			case "UD":

				history.setFlag("유저가 삭제되었습니다.");

				break;

			case "FC":

				fname=userDao.selectUserByUserSeqDao(history.getFSeq()).getName();

				history.setFlag("친구 ["+fname+"] (이)가 추가되었습니다.");

				break;

			case "FD":

				fname=userDao.selectUserByUserSeqDao(history.getFSeq()).getName();

				history.setFlag("친구 ["+fname+"] (이)가 삭제되었습니다.");

				break;

			case "GC":

				gname=groupdao.selectGroupByGroupSeqDao(history.getGSeq()).getName();

				history.setFlag("그룹 ["+gname+"] (이)가 추가되었습니다.");

				break;

			case "GU":

				gname=groupdao.selectGroupByGroupSeqDao(history.getGSeq()).getName();

				history.setFlag("그룹 ["+gname+"] (이)가 변경되었습니다.");

				break;

			case "GD":

				gname=groupdao.selectGroupByGroupSeqDao(history.getGSeq()).getName();

				history.setFlag("그룹 ["+gname+"] (이)가 삭제되었습니다.");

				break;

			case "GFC":

				fname=userDao.selectUserByUserSeqDao(history.getFSeq()).getName();

				gname=groupdao.selectGroupByGroupSeqDao(history.getGSeq()).getName();

				history.setFlag("그룹 ["+gname+"]에서 친구 ["+fname+"](이)가 추가되었습니다.");

				break;

			case "GFD":

				fname=userDao.selectUserByUserSeqDao(history.getFSeq()).getName();

				gname= groupdao.selectGroupByGroupSeqDao(history.getGSeq()).getName();

				history.setFlag("그룹 ["+gname+"]에서 친구 ["+fname+"](이)가 삭제되었습니다.");

				break;

			default:

				break;

			}

		}

		return historyList;
	}
	
	public List<UserItem> selectUserNotFriendByUserNameService(int uSeq, String name) {
	    return userDao.selectUserNotFriendByUserNameDao(uSeq, name);
	}
}
