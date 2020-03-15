package io.myoung.sample.exception;

/**
 * GroupException.java
 * @클래스설명 : 그룹 발생 시 사용하는 클래스
 */
public class GroupException extends RuntimeException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4111776499751849295L;

	public GroupException(Exception e) {
        super(e);
    }

	public GroupException(String message) {
		super(message);
	}
}
