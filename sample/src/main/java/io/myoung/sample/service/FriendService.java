package io.myoung.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.myoung.sample.dao.FriendDao;
import io.myoung.sample.dao.GroupDao;
import io.myoung.sample.exception.FriendException;
import io.myoung.sample.exception.GroupException;
import io.myoung.sample.model.GroupItem;
import io.myoung.sample.model.UserItem;


/**
 * FriendService.java
 * @클래스설명 : 주소록을 위한 서비스 클래스
 */
@Service
public class FriendService {
	@Autowired
	private FriendDao friendDao;
	@Autowired
	private GroupDao groupDao;

	/**
	 * @메소드설명 : Dao에 그룹 키를 가지고 그룹에 속한 유저를 가져온다.
	 * @param gSeq : 그룹의 키
	 * @return : 그룹에 속한 모든 유저
	 */
	public List<UserItem> selectFriendByGroupSeqService(int gSeq) {
		return friendDao.selectFriendByGroupSeqDao(gSeq);
	}
	
	/**
	 * @메소드설명 : 그룹 키를 가지고 그룹을 조회하여 그룹이 '전체'그룹인지 확인 한다.
	 *           만약 '전체'그룹일 때 유저를 사용자의 주소록에 등록 한다. '전체'그룹이 아니라면 전달된 그룹에만 등록 한다.
	 * @param fSeq : 주소록에 등록할 유저 키
	 * @param gSeq : 그룹의 키
	 * @return : 등록 결과(0,1)
	 */
	@Transactional
	public Integer insertFriendByFriendSeqService(int fSeq, int gSeq) throws EmptyResultDataAccessException {
		GroupItem groupItem = groupDao.selectGroupByGroupSeqDao(gSeq);
		
		if(groupItem == null) {
			throw new GroupException("그룹이 존재하지 않습니다.");
		}
		
		int count;
		if(groupItem.getName().equals("전체")) {
			count = friendDao.insertFriendByFriendSeqDao(fSeq, groupItem.getUSeq());
			if(count == 0) {
				throw new FriendException("주소록에 추가하지 못했습니다.");
			}
		}
		
		count = friendDao.insertFriendToGroupByFriendSeqDao(fSeq, gSeq);
		if(count == 0) {
			throw new FriendException("주소록에 추가하지 못했습니다.");
		}
		
		return count;
	}
	
	
}
