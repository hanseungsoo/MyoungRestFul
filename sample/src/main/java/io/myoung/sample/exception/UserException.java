package io.myoung.sample.exception;

/**
 * UserException.java
 * @클래스설명 : 사용자 관련 시 사용하는 클래스
 */
public class UserException extends RuntimeException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2527276083439148266L;

	public UserException(Exception e) {
        super(e);
    }

	public UserException(String message) {
		super(message);
	}
}
