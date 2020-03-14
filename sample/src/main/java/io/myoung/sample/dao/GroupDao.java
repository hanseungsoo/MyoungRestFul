package io.myoung.sample.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import io.myoung.sample.mapper.GroupMapper;
import io.myoung.sample.model.GroupItem;

@Repository
public class GroupDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<GroupItem> selectGroupListByUserSeqDao(int uSeq) {
		
		try {
			return jdbcTemplate.queryForObject(
	                "select * from TB_GROUP where U_SEQ = ?", new GroupMapper(), uSeq);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
    }
	
	public Integer insertGroupByUserSeqDao(GroupItem item) {
		
		return jdbcTemplate.update(
                "insert into TB_GROUP(G_SEQ, U_SEQ, NAME)" +
                "values(g_seq.nextval, ?, ?)", item.getUSeq(), item.getName());
    }
	
	
}
