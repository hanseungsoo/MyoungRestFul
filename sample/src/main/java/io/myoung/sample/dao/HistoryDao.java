package io.myoung.sample.dao;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import io.myoung.sample.model.HistoryItem;

/**
 * HistoryDao.java
 * @클래스설명 : 이력 관리와 관련된 테이블에 접근하는 클래스 
 */
@Repository
public class HistoryDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Integer insertHistory(HistoryItem history) {
		BigDecimal uSeq = history.getUSeq()==0?null:new BigDecimal(history.getUSeq());
		BigDecimal fSeq = history.getFSeq()==0?null:new BigDecimal(history.getFSeq());
		BigDecimal gSeq = history.getGSeq()==0?null:new BigDecimal(history.getGSeq());
		return jdbcTemplate.update(
            "insert into TB_HISTORY(H_SEQ, FLAG, ACTION_TIME, U_SEQ, F_SEQ, G_SEQ)" +
            "values(g_seq.nextval, ?, SYSDATE,? ,? ,?)"
            , history.getFlag(), uSeq, fSeq, gSeq);
	}
}
