package io.myoung.sample.config.datasource;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import lombok.Data;


/**
 * DataSourceConfig.java
 * @클래스설명 : DB 접속을 위한 설정 클래스
 */
@Configuration	
@Data
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceConfig {

	private String url;
	private String username;
	private String password;
	private String driverClassName;
	
	@Bean
    public DataSource dataSource() {
        return DataSourceBuilder
        		.create()
        		.driverClassName(driverClassName)
        		.url(url)
        		.username(username)
        		.password(password)
        		.build();
    }
	
	@Bean
	public JdbcTemplate createJdbcTemplate(DataSource ds) { 
		return new JdbcTemplate(ds); 
	}
}
