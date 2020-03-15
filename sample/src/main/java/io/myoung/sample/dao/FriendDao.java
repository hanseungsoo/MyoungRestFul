package io.myoung.sample.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import io.myoung.sample.mapper.FriendUserMapper;
import io.myoung.sample.model.UserItem;

@Repository
public class FriendDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<UserItem> selectFriendByGroupSeqDao(int gSeq) {
		
		return jdbcTemplate.query(
                "select b.* from TB_GROUP_TO_FRIEND a " +
                "left join TB_USER b on (a.G_SEQ = ? and a.F_SEQ = b.U_SEQ)", new Object[] {gSeq}, new FriendUserMapper());
    }
	
	public Integer insertFriendToGroupByFriendSeqDao(int fSeq, int gSeq) {
		
		return jdbcTemplate.update(
                "insert into TB_GROUP_TO_FRIEND(G_SEQ, F_SEQ)" +
                "values(?, ?)", gSeq, fSeq);
    }
	
	public Integer insertFriendByFriendSeqDao(int fSeq, int uSeq) {
		
		return jdbcTemplate.update(
                "insert into TB_FRIEND(U_SEQ, F_SEQ)" +
                "values(?, ?)", uSeq, fSeq);
    }
	
}
