package io.myoung.sample.exception;

public class UserException extends RuntimeException  {

	public UserException(Exception e) {
        super(e);
    }

	public UserException(String message) {
		super(message);
	}
}
