package com.test.exception;

@SuppressWarnings("serial")
public class PwMissMatchException extends Exception {
	public PwMissMatchException(String args){
		super(args);
	}
}
