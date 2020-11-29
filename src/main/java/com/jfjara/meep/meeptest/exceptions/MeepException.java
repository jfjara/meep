package com.jfjara.meep.meeptest.exceptions;

public class MeepException extends Exception {

	private static final long serialVersionUID = 7124237313274197527L;
	
	private String customMessage;
	
	public MeepException(String msg) {
		this.customMessage = msg;
	}
	
	public String getCustomMessage() {
		return customMessage;
	}
	

}
