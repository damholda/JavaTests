package com.byhiras.auctiontool.api;

public class User {
	
	private final int id;
	private String username;
	private String firstName;
	private String lastName;
	
	public User(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
