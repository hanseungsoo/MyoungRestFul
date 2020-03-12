package io.myoung.sample.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import io.myoung.sample.model.GroupItem;

public class GroupMapper implements RowMapper<List<GroupItem>> {

	@Override
	public List<GroupItem> mapRow(ResultSet rs, int rowNum) throws SQLException {
		List<GroupItem> list = new ArrayList<GroupItem>();
		
		while(rs.next()) {
			list.add(GroupItem.builder().groupId(rs.getInt(0)).groupName(rs.getString(1)).build());
		}
		
		return list;
	}

}
