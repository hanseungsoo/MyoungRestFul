package io.myoung.sample.exception;

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
