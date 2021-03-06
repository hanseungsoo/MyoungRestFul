package io.myoung.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.myoung.sample.dao.GroupDao;
import io.myoung.sample.dao.HistoryDao;
import io.myoung.sample.exception.GroupException;
import io.myoung.sample.model.GroupItem;
import io.myoung.sample.model.HistoryItem;

/**
 * GroupService.java
 * @클래스설명 : 그룹을 위한 서비스 클래스
 */
@Service
public class GroupService {
	@Autowired
	private GroupDao groupDao;
	@Autowired
	private HistoryDao historyDao;

	/**
	 * @메소드설명 : 사용자 키로 사용자가 가진 그룹 리스트를 가져 온다.
	 * @param uSeq : 사용자 키
	 * @return : 그룹 리스트
	 */
	public List<GroupItem> selectGroupListByUserSeqService(int uSeq) {
		return groupDao.selectGroupListByUserSeqDao(uSeq);
	}
	
//	public List<UserItem> selectUserListbyGroupSeqService(int uSeq, int gSeq) {
//		return groupDao.selectGroupListByUserSeqDao(uSeq, gSeq);
//	}
	
	/**
	 * @메소드설명 : 새로운 그룹을 등록 한다.
	 * @param uSeq : 사용자 키
	 * @param name : 새로운 그룹 명
	 * @return : 등록 결과(0,1)
	 */
	@Transactional
	public GroupItem insertGroupByUserSeqService(int uSeq, String name) {
		int count = groupDao.insertGroupByUserSeqDao(uSeq, name);
		GroupItem group = null;
		if(count == 0) {
			throw new GroupException("그룹 생성에 실패하였습니다.");
		}else if (count == 1) {
			group = groupDao.selectGroupByUseqnNameDao(uSeq, name);
			int gSeq = group.getGSeq();
			historyDao.insertHistory(HistoryItem.builder().flag("GC").gSeq(gSeq).uSeq(uSeq).build());
		}
		return group;
	}
	
	@Transactional
	public Integer deleteGroupByGseqService(int gSeq, int uSeq) {
		groupDao.deleteFriendtoGroupByGSeqDao(gSeq);
		int count = groupDao.deleteGroupByGseqDao(gSeq);
		
		if (count != 0) {
			historyDao.insertHistory(HistoryItem.builder().flag("GD").gSeq(gSeq).uSeq(uSeq).build());
		}
		return count;
	}
}
