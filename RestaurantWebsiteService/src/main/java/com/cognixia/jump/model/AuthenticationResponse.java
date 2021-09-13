package com.cognixia.jump.model;

//response given in authentication endpoint, gives back jwt
public class AuthenticationResponse {

	private final String jwt;
	
	public AuthenticationResponse(String jwt) {
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}
	
}
