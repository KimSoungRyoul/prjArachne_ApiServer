package org.prj.arachne;

import org.prj.arachne.presentation.dto.AuthenticationRequest;

import com.google.gson.Gson;

public class TestInMain {

	public static void main(String[] args) {
		
		AuthenticationRequest authenticationRequest=new AuthenticationRequest();
		
		authenticationRequest.setUserEmail("KimSoungRyoul@gmail.com");
		authenticationRequest.setPassword("12345");
		
		System.out.println(new Gson().toJson(authenticationRequest).toString());
		
		
	}
}
