package io.myoung.sample.model;

import lombok.Data;

@Data
public class JwtTokenItem {
	public JwtTokenItem(String token) {
		this.token = token;
	}
	private String token;
}
