package io.myoung.sample.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import io.myoung.sample.model.GroupItem;

/**
 * GroupMapper.java
 * @클래스설명 : 그룹 정보를 가져오는 클래스
 */
public class GroupMapper implements RowMapper<GroupItem> {

	@Override
	public GroupItem mapRow(ResultSet rs, int rowNum) throws SQLException {
		GroupItem item = new GroupItem();
		
		item.setGSeq(rs.getInt("G_SEQ"));
		item.setUSeq(rs.getInt("U_SEQ"));
		item.setName(rs.getString("NAME"));
		
		return item;
	}

}
