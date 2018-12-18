package com.test.common.exception;

@SuppressWarnings("serial")
public class DataAccessException extends RuntimeException{
	public DataAccessException(String msg, Exception e){ super(msg +e); }
	public DataAccessException(String msg){ super(msg); }
}
