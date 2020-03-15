package io.myoung.sample.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import io.myoung.sample.model.FriendItem;

public class FriendMapper implements RowMapper<FriendItem> {

	@Override
	public FriendItem mapRow(ResultSet rs, int rowNum) throws SQLException {
		FriendItem item = new FriendItem();
		
		item.setFSeq(rs.getInt("F_SEQ"));
		item.setUSeq(rs.getInt("U_SEQ"));
		
		return item;
	}

}
