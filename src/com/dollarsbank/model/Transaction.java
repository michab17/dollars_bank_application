package com.dollarsbank.model;

import java.time.LocalDateTime;

import com.dollarsbank.utility.ConsoleColors;

public class Transaction {
	private String type;
	
	private String description;
	
	private LocalDateTime timestamp;

	public Transaction(String type, String description, LocalDateTime timestamp) {
		super();
		this.type = type;
		this.description = description;
		this.timestamp = timestamp;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	@Override
	public String toString() {
		return ConsoleColors.WHITE + "Transaction type: " + type + "\n Transaction description: " + description + "\n Time of transaction: " + timestamp;
	}
	
}
