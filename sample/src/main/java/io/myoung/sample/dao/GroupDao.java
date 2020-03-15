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
		
		return jdbcTemplate.query(
                "select * from TB_GROUP where U_SEQ = ?", new Object[] {uSeq}, new GroupMapper());
    }
	
	public Integer insertGroupByUserSeqDao(int uSeq, String name) {
		
		return jdbcTemplate.update(
                "insert into TB_GROUP(G_SEQ, U_SEQ, NAME)" +
                "values(g_seq.nextval, ?, ?)", uSeq, name);
    }
	
	public GroupItem selectGroupByGroupSeqDao(int gSeq) {
		try {
			return jdbcTemplate.queryForObject(
	                "select * from TB_GROUP where G_SEQ = ?", new GroupMapper(), gSeq);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
    }
}
