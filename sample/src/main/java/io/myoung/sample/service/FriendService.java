package io.myoung.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.myoung.sample.dao.FriendDao;
import io.myoung.sample.dao.GroupDao;
import io.myoung.sample.exception.FriendException;
import io.myoung.sample.exception.GroupException;
import io.myoung.sample.model.GroupItem;
import io.myoung.sample.model.UserItem;

@Service
public class FriendService {
	@Autowired
	private FriendDao friendDao;
	@Autowired
	private GroupDao groupDao;

	public List<UserItem> selectFriendByGroupSeqService(int gSeq) {
		return friendDao.selectFriendByGroupSeqDao(gSeq);
	}
	
	@Transactional
	public Integer insertFriendByFriendSeqService(int fSeq, int gSeq) {
		GroupItem groupItem = groupDao.selectGroupByGroupSeqDao(gSeq);
		
		if(groupItem == null) {
			throw new GroupException("그룹이 존재하지 않습니다.");
		}
		
		if(groupItem.getName().equals("전체")) {
			int count;
			count = friendDao.insertFriendByFriendSeqDao(fSeq, groupItem.getUSeq());
			if(count == 0) {
				throw new FriendException("주소록에 추가하지 못했습니다.");
			}
		}
		
		return friendDao.insertFriendToGroupByFriendSeqDao(fSeq, gSeq);
	}
	
	
}
