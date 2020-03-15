package io.myoung.sample.exception;

public class EncryptException extends RuntimeException  {

	public EncryptException(Exception e) {
        super(e);
    }
}
