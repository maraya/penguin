package com.googlecode.penguin.utils;

public class ServiceException extends Exception {		
	private static final long serialVersionUID = -7506488876889957798L;

	public ServiceException (String service) {
		super("Failed to obtain " + service + " service");
	}
}
