package io.myoung.sample.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * UserItem.java
 * @클래스설명 : 사용자 ITEM 클래스 
 */
@Data
public class UserItem {
	
	public UserItem() {
	}
	
	private int uSeq;
	private String role;
	
	@NotNull(message = "Name may not be null")
	@NotBlank(message = "Name may not be blank")
	@Size(max = 20)
	private String name;
	
	@NotNull(message = "Id may not be null")
	@NotBlank(message = "Id may not be blank")
	@Size(max = 10)
	private String id;

	@NotNull(message = "Password may not be null")
	@NotBlank(message = "Password may not be blank")
	@Size(max = 24)
	private String password;
	
	@NotNull(message = "Phone may not be null")
	@NotBlank(message = "Phone may not be blank")
	@Size(max = 20)
	private String phone;
	
	@Email(message = "Email should be valid")
	@NotNull(message = "Email may not be null")
	@NotBlank(message = "Email may not be blank")
	@Size(max = 20)
	private String email;
}
