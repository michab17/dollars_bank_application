package com.dollarsbank.model;

import java.util.ArrayList;

import com.dollarsbank.utility.ConsoleColors;

public class Account {
	
	private String name;
	
	private String address;
	
	private String phoneNumber;
	
	private String username;
	
	private String password;
	
	private Double availableFunds;
	
	public ArrayList<Transaction> transactions = new ArrayList<Transaction>();

	public Account(String name, String address, String phoneNumber, String username, String password,
			Double availableFunds) {
		super();
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.username = username;
		this.password = password;
		this.availableFunds = availableFunds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Double getAvailableFunds() {
		return availableFunds;
	}

	public void setAvailableFunds(Double available_funds) {
		this.availableFunds = available_funds;
	}
	
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	@Override
	public String toString() {
		return ConsoleColors.WHITE + "Name: " + name + "\nAddress: " + address + "\nPhone Number: " + phoneNumber + "\nUser Id: "
				+ username + "\nPassword: " + password + "\nAvailable Funds: " + availableFunds;
	}

}
