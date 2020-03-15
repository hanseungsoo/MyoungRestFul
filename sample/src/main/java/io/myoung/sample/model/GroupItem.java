package io.myoung.sample.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * GroupItem.java
 * @클래스설명 : 그룹을 위한 ITEM 클래스
 */
@Data
public class GroupItem {
	
	private int gSeq;
	private int uSeq;
	
	@NotNull(message = "Name may not be null")
	@NotBlank(message = "Name may not be blank")
	private String name;
}
