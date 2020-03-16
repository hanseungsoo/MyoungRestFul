package io.myoung.sample.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import io.myoung.sample.mapper.FriendUserMapper;
import io.myoung.sample.model.UserItem;


/**
 * FriendDao.java
 * @클래스설명 : 주소록과 관련된 테이블에 접근하는 클래스
 */
@Repository
public class FriendDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * @메소드설명 : TB_GROUP_TO_FRIEND와 TB_USER를 조인하여 그룹에 속한 모든 유저를 가져 온다.
	 * @param gSeq : 그룹의 키
	 * @return 그룹에 속한 모든 유저 정보
	 */
	public List<UserItem> selectFriendByGroupSeqDao(int gSeq) {
		
		return jdbcTemplate.query(
                "select b.* from TB_GROUP_TO_FRIEND a " +
                "inner join TB_USER b on (a.G_SEQ = ? and a.F_SEQ = b.U_SEQ)", new Object[] {gSeq}, new FriendUserMapper());
    }
	
	/**
	 * @메소드설명 : 그룹과 내 주소록을 연결하는 TB_GROUP_TO_FRIEND 테이블에 데이터를 입력 한다.
	 * @param fSeq : 주소록에 등록할 유저 키
	 * @param gSeq : 그룹의 키
	 * @return Insert 결과(0,1)
	 */
	public Integer insertFriendToGroupByFriendSeqDao(int fSeq, int gSeq) {
		
		return jdbcTemplate.update(
                "insert into TB_GROUP_TO_FRIEND(G_SEQ, F_SEQ)" +
                "values(?, ?)", gSeq, fSeq);
    }
	
	/**
	 * @메소드설명 : 특정 유저를 내 주소록에 추가 한다.
	 * @param fSeq : 주소록에 추가할 유저 키
	 * @param uSeq : 사용자 키
	 * @return Insert 결과 (0,1)
	 */
	public Integer insertFriendByFriendSeqDao(int fSeq, int uSeq) {
		
		return jdbcTemplate.update(
                "insert into TB_FRIEND(U_SEQ, F_SEQ)" +
                "values(?, ?)", uSeq, fSeq);
    }
	
}
