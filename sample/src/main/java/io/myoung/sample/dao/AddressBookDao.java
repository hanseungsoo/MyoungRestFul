package io.myoung.sample.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import io.myoung.sample.mapper.GroupMapper;
import io.myoung.sample.model.GroupItem;

@Repository
public class AddressBookDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<GroupItem> findGroupListByUserIdDao(int id) {
        return jdbcTemplate.queryForObject(
                "select * from books where id = ?", new GroupMapper());
    }
}
