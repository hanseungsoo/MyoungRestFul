package io.myoung.sample.exception;

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
