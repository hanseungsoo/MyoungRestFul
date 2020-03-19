package io.myoung.sample.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

import lombok.Data;

/**
 * DuplGroupItem.java
 * @클래스설명 : 중복 그룹 클래스 
 */
@Data
public class DuplGroupItem {

	private int duplCnt;
	private String duplColumn;

}
