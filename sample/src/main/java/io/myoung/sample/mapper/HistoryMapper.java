package io.myoung.sample.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import io.myoung.sample.model.FriendItem;
import io.myoung.sample.model.HistoryItem;

/**
 * HistoryMapper.java
 * @클래스설명 : 이력관리 테이블 매핑 클래스
 */
public class HistoryMapper implements RowMapper<HistoryItem> {

	@Override
	public HistoryItem mapRow(ResultSet rs, int rowNum) throws SQLException {
		int hSeq = rs.getInt("H_SEQ");
		Date date = rs.getDate("ACTION_TIME");
		String flag = rs.getString("FLAG");
		int fSeq = rs.getInt("F_SEQ");
		int uSeq = rs.getInt("U_SEQ");
		int gSeq = rs.getInt("G_SEQ");

		return HistoryItem.builder().hSeq(hSeq).actionTime(date).flag(flag).fSeq(fSeq).uSeq(uSeq).gSeq(gSeq).build();
	}

}
