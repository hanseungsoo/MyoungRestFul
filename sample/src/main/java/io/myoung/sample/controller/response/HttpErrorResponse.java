package io.myoung.sample.controller.response;

import java.util.List;

import io.myoung.sample.model.ErrorItem;
import io.myoung.sample.value.StatusEnum;
import lombok.Builder;
import lombok.Data;

/**
 * HttpErrorResponse.java
 * @클래스설명 : 에러 응답을 담아 보내는 클래스
 */
@Data
@Builder
public class HttpErrorResponse {
	private String message;
	private StatusEnum status;
	private List<ErrorItem> errors;
}
