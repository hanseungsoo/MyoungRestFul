package io.myoung.sample.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import io.myoung.sample.model.UserItem;

public class UserMapper implements RowMapper<UserItem> {

	@Override
	public UserItem mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserItem item = new UserItem();
		
		item.setUSeq(rs.getInt("U_SEQ"));
		item.setName(rs.getString("NAME"));
		item.setId(rs.getString("ID"));
		item.setPassword(rs.getString("PASSWORD"));
		item.setEmail(rs.getString("EMAIL"));
		item.setPhone(rs.getString("PHONE"));
		
		return item;
	}

}
