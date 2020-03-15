package io.myoung.sample.exception;

public class EncryptException extends RuntimeException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4814103873537518029L;

	public EncryptException(Exception e) {
        super(e);
    }
}
