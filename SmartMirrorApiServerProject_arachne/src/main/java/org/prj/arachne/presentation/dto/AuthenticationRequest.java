package org.prj.arachne.presentation.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {

	private String userEmail;
	private String password;
}
