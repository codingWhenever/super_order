
package com.e_at.eatlibrary.injection;

public class EaiException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public EaiException() {
		super();
	}
	
	public EaiException(String msg) {
		super(msg);
	}
	
	public EaiException(Throwable ex) {
		super(ex);
	}
	
	public EaiException(String msg, Throwable ex) {
		super(msg,ex);
	}

}
