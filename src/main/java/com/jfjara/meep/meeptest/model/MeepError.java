package com.jfjara.meep.meeptest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MeepError {

	private String message;
	private int status;
	private String uri;
	
	
}
