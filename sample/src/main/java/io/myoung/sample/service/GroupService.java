package io.myoung.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.myoung.sample.dao.GroupDao;
import io.myoung.sample.model.GroupItem;

@Service
public class GroupService {
	@Autowired
	private GroupDao groupDao;

	public List<GroupItem> selectGroupListByUserSeqService(int uSeq) {
		return groupDao.selectGroupListByUserSeqDao(uSeq);
	}
	
//	public List<UserItem> selectUserListbyGroupSeqService(int uSeq, int gSeq) {
//		return groupDao.selectGroupListByUserSeqDao(uSeq, gSeq);
//	}
	
	public Integer insertGroupByUserSeqService(int uSeq, String name) {
		return groupDao.insertGroupByUserSeqDao(uSeq, name);
	}
}
