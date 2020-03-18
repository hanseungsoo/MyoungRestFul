package io.myoung.sample.controller.response;

import io.myoung.sample.value.StatusEnum;
import lombok.Builder;
import lombok.Data;

/**
 * HttpSuccessResponse.java
 * @클래스설명 : 정상 응답을 담아 보내는 클래스
 */
@Data
@Builder
public class ViewSuccessResponse<T> {
	private StatusEnum status;
	private T data;
	private int draw;
	private int recordsTotal;
	private int recordsFiltered;
}
