package io.myoung.sample.controller.response;

import lombok.Builder;
import lombok.Data;

/**
 * HttpSuccessResponse.java
 * @클래스설명 : 
 */
@Data
@Builder
public class HttpSuccessResponse<T> {
	private T data;
}
