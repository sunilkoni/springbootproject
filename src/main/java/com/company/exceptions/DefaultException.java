package com.company.exceptions;

import java.util.Date;

public class DefaultException extends RuntimeException {
	private Date date;
	
	private String message;
	
	private String details;

	public DefaultException(Date date, String message, String details) {
		super();
		this.date = date;
		this.message = message;
		this.details = details;
	}

	public Date getDate() {
		return date;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	@Override
	public String toString() {
		return "DefaultException [date=" + date + ", message=" + message + ", details=" + details + "]";
	}
	
}
