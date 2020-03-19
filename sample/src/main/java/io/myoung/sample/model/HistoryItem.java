package io.myoung.sample.model;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

/**
 * HistoryItem.java
 * @클래스설명 : 변경 이력 위한 ITEM 클래스 
 */
@Data
@Builder
public class HistoryItem {

	private int hSeq;
	private String flag;
	private Date actionTime;
	private int uSeq;
	private int fSeq;
	private int gSeq;
	
}