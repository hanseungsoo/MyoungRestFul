package io.myoung.sample.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import io.myoung.sample.model.GroupItem;
import io.myoung.sample.model.UserItem;

public class UserMapper implements RowMapper<UserItem> {

	@Override
	public UserItem mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserItem item = new UserItem();
		
		if(rs.next()) {
			item.setUSeq(rs.getInt(0));
			item.setName(rs.getString(1));
			item.setId(rs.getString(2));
			item.setPassword(rs.getString(3));
			item.setEmail(rs.getString(4));
			item.setPhone(rs.getString(5));
		} else {
			return null;
		}
		
		return item;
	}

}
