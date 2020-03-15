package io.myoung.sample.exception;

/**
 * FriendException.java
 * @클래스설명 : 주소록 관련 에러 발생 시 사용하는 클래스
 */
public class FriendException extends RuntimeException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4248603116583110355L;

	public FriendException(Exception e) {
        super(e);
    }

	public FriendException(String message) {
		super(message);
	}
}
