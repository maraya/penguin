package com.googlecode.penguin.utils;

public class ActionException extends Exception {
	private static final long serialVersionUID = -7577319121145917590L;
	
	public ActionException (String action, String msg) {
		super("Failed to execute " + action + " action: " + msg);
	}
}