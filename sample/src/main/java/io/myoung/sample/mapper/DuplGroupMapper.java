package io.myoung.sample.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import io.myoung.sample.model.DuplGroupItem;


/**
 * DuplGroupMapper.java
 * @클래스설명 : 중복된 그룹을 가져오는 매퍼
 */
public class DuplGroupMapper implements RowMapper<DuplGroupItem> {

	@Override
	public DuplGroupItem mapRow(ResultSet rs, int rowNum) throws SQLException {
		DuplGroupItem item = new DuplGroupItem();
		ResultSetMetaData rsmd = rs.getMetaData();
		String name = rsmd.getColumnName(1);
		item.setDuplCnt(rs.getInt("COUNT"));
		item.setDuplColumn(rs.getString(name));
		
		return item;
	}

}
