package io.myoung.sample.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import io.myoung.sample.model.UserItem;

/**
 * FriendUserMapper.java
 * @클래스설명 : 주소록에 있는 유저 정보를 가져오는 클래스
 */
public class FriendUserMapper implements RowMapper<UserItem> {

	@Override
	public UserItem mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserItem item = new UserItem();
		
		item.setUSeq(rs.getInt("U_SEQ"));
		item.setName(rs.getString("NAME"));
		item.setPhone(rs.getString("PHONE"));
		item.setEmail(rs.getString("EMAIL"));
		item.setId(rs.getString("ID"));
		
		return item;
	}

}
