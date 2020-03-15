package io.myoung.sample.exception;

public class GroupException extends RuntimeException  {

	public GroupException(Exception e) {
        super(e);
    }

	public GroupException(String message) {
		super(message);
	}
}
