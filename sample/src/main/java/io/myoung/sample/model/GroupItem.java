package io.myoung.sample.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GroupItem {
	
	private int gSeq;
	private int uSeq;
	
	@NotNull(message = "Name may not be null")
	@NotBlank(message = "Name may not be blank")
	private String name;
}
