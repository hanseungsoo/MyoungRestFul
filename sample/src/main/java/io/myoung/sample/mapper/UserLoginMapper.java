package io.myoung.sample.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import io.myoung.sample.security.CustomUserDetails;

/**
 * UserMapper.java
 * @클래스설명 : 사용자 정보를 가져오는 클래스
 */
public class UserLoginMapper implements RowMapper<CustomUserDetails> {

	@Override
	public CustomUserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		CustomUserDetails item = new CustomUserDetails();
		
		item.setUsername(rs.getString("ID"));
		item.setPassword(rs.getString("PASSWORD"));
		item.setUserSeq(rs.getInt("U_SEQ"));
		
		return item;
	}

}
