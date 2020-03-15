package io.myoung.sample.exception;

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
