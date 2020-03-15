package io.myoung.sample.model;

import lombok.Data;

/**
 * ErrorItem.java
 * @클래스설명 : 세부 에러 세팅을 위한 ITEM 클래스 
 */
@Data
public class ErrorItem {
	private String field;
	private Object value;
	private String reason;
}
