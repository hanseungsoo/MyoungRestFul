package io.myoung.sample.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import io.myoung.sample.model.UserItem;

/**
 * UserMapper.java
 * @클래스설명 : 사용자 정보를 가져오는 클래스
 */
public class UserMapper implements RowMapper<UserItem> {

	@Override
	public UserItem mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserItem item = new UserItem();
		item.setUSeq(rs.getInt("U_SEQ"));
		item.setName(rs.getString("NAME"));
		item.setEmail(rs.getString("EMAIL"));
		item.setPhone(rs.getString("PHONE"));
		item.setRole(rs.getString("ROLE"));
		
		return item;
	}

}
