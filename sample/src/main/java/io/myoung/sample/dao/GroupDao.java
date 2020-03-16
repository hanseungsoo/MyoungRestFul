package io.myoung.sample.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import io.myoung.sample.mapper.GroupMapper;
import io.myoung.sample.model.GroupItem;

/**
 * GroupDao.java
 * @클래스설명 : 그룹과 관련된 테이블에 접근하는 클래스 
 */
@Repository
public class GroupDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * @메소드설명 : 특정 그룹에 속한 모든 유저 정보를 가져 온다.
	 * @param uSeq : 사용자 키
	 * @return : 그룹 리스트
	 */
	public List<GroupItem> selectGroupListByUserSeqDao(int uSeq) {
		
		return jdbcTemplate.query(
                "select * from TB_GROUP where U_SEQ = ?", new Object[] {uSeq}, new GroupMapper());
    }
	
	/**
	 * @메소드설명 : 새로운 그룹을 등록 한다.
	 * @param uSeq : 사용자 키
	 * @param name : 새로운 그룹 명
	 * @return : Insert 결과(0,1)
	 */
	public Integer insertGroupByUserSeqDao(int uSeq, String name) {
		
		return jdbcTemplate.update(
                "insert into TB_GROUP(G_SEQ, U_SEQ, NAME)" +
                "values(g_seq.nextval, ?, ?)", uSeq, name);
    }
	
	/**
	 * @메소드설명 : 그룹의 키로 그룹 정보를 가져온다.
	 * @param gSeq : 그룹의 키
	 * @return 그룹 키가 가진 그룹의 정보
	 */
	public GroupItem selectGroupByGroupSeqDao(int gSeq) throws EmptyResultDataAccessException {
		return jdbcTemplate.queryForObject(
                "select * from TB_GROUP where G_SEQ = ?", new GroupMapper(), gSeq);
    }
	
	public GroupItem selectGroupByUserNameDao(String name) throws EmptyResultDataAccessException {
		return jdbcTemplate.queryForObject(
                "select count(1) as cnt from from TB_USER a " +
                "inner join TB_GROUP b on (a.NAME = '한승수' and a.U_SEQ = b.U_SEQ) where NAME = ?", new GroupMapper(), name);
    }
}
