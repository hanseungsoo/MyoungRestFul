package io.myoung.sample.exception;

public class FriendException extends RuntimeException  {

	public FriendException(Exception e) {
        super(e);
    }

	public FriendException(String message) {
		super(message);
	}
}
