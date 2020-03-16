package io.myoung.sample.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import io.myoung.sample.mapper.UserLoginMapper;
import io.myoung.sample.security.UserDetailsImpl;


/**
 * UserLoginDao.java
 * @클래스설명 : 로그인과 관련된 테이블에 접근하는 클래스 
 */
@Repository
public class UserLoginDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public UserDetailsImpl selectLoginByUserId(String eMail) throws EmptyResultDataAccessException {
		
		return jdbcTemplate.queryForObject(
                "select * from TB_USER where EMAIL = ?", new UserLoginMapper(), eMail);
    }
	
}
