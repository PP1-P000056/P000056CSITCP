package com.example.project000056.payload.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
	@NotBlank
  private String username;

	@NotBlank
	private String password;
	private int role;

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

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

}
