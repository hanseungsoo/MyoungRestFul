package io.myoung.sample.controller.request;

import lombok.Data;

@Data
public class JwtRequest {
	private String email;
	private String password;
}
