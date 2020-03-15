package io.myoung.sample.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import io.myoung.sample.mapper.UserMapper;
import io.myoung.sample.model.UserItem;

@Repository
public class UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Integer insertUserDao(UserItem item) {
		return jdbcTemplate.update(
                "insert into TB_USER(U_SEQ, NAME, ID, PASSWORD, EMAIL, PHONE)" +
                "values(u_seq.nextval, ?, ?, ?, ?, ?)", item.getName(), item.getId(), item.getPassword(), item.getEmail(), item.getPhone());
    }
	
	public UserItem selectUserDao(int uSeq) {
		try {
			return jdbcTemplate.queryForObject(
	                "select * from TB_USER where U_SEQ = ?", new UserMapper(), uSeq);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
    }
	
}
