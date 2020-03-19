package io.myoung.sample.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import io.myoung.sample.mapper.HistoryMapper;
import io.myoung.sample.mapper.UserMapper;
import io.myoung.sample.model.HistoryItem;
import io.myoung.sample.model.UserItem;

/**
 * UserDao.java
 * @클래스설명 : 사용자와 관련된 테이블에 접근하는 클래스 
 */
@Repository
public class UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * @메소드설명 : 사용자 정보를 받아서 회원 가입 처리를 한다.
	 * @param item : 사용자 정보
	 * @return : Insert 결과(0,1)
	 */
	public Integer insertUserDao(UserItem item) {
		return jdbcTemplate.update(
                "insert into TB_USER(U_SEQ, NAME, PASSWORD, EMAIL, PHONE, ROLE)" +
                "values(u_seq.nextval, ?, ?, ?, ?, ?)", item.getName(), item.getPassword(), item.getEmail(), item.getPhone(), item.getRole());
    }
	
	/**
	 * @메소드설명 : 사용자 키로 사용자 정보를 가져 온다.
	 * @param uSeq : 사용자 키
	 * @return : 사용자 정보
	 */
	public UserItem selectUserByUserSeqDao(int uSeq) throws EmptyResultDataAccessException {
		return jdbcTemplate.queryForObject(
                "select * from TB_USER where U_SEQ = ?", new UserMapper(), uSeq);
    }
	
	public List<UserItem> selectUserByNotUserSeqDao(int uSeq) throws EmptyResultDataAccessException {
		return jdbcTemplate.query(
                "select * from TB_USER where U_SEQ != ?", new UserMapper(), uSeq);
    }
	
	/**
	 * @메소드설명 : 유저 이름으로 유저 정보를 조회 한다.
	 * @param uName : 유저 이름
	 * @return : 이름으로 검색된 유저 정보 리스트
	 */
	public List<UserItem> selectUserByUnameDao(String uName) {
		return jdbcTemplate.query(
                "select * from TB_USER where NAME like ?", new Object[] {"%" + uName + "%"}, new UserMapper());
    }
	
	public UserItem selectUserByEmailDao(String email) {
		System.out.println("################여기는 select");
		return jdbcTemplate.query(
                "select * from TB_USER where Email = ?", new Object[] {email}, new UserMapper()).get(0);
    }
	
	public List<HistoryItem> selectHistoryByUseqDao(int uSeq){
		return jdbcTemplate.query(
                "select * from TB_HISTORY where U_SEQ like ?", new Object[] {uSeq}, new HistoryMapper());
		
	}
	public List<UserItem> selectUserNotFriendByUserNameDao(int uSeq, String name) throws EmptyResultDataAccessException {
		  return jdbcTemplate.query(
		            "select * from TB_USER where U_SEQ != ? AND NAME like ? AND U_SEQ not in (select F_SEQ from TB_FRIEND where U_SEQ=?)", new Object[] {uSeq, "%" + name + "%", uSeq}, new UserMapper());	
		}
	
}
