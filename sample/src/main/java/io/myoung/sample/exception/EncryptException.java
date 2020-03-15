package io.myoung.sample.exception;

/**
 * EncryptException.java
 * @클래스설명 : 암호화 에러 발생시 사용하는 클래스
 */
public class EncryptException extends RuntimeException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4814103873537518029L;

	public EncryptException(Exception e) {
        super(e);
    }
}
