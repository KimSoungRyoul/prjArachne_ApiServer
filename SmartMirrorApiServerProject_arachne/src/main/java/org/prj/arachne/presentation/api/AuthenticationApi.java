package org.prj.arachne.presentation.api;

import javax.servlet.http.HttpSession;

import org.prj.arachne.application.MemberAuthenticationService;
import org.prj.arachne.model.member.MemberAccount;
import org.prj.arachne.presentation.dto.AuthenticationRequest;
import org.prj.arachne.presentation.dto.AuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthenticationApi {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	MemberAuthenticationService memberAuthService;

	
	
	
	
	@PostMapping("/authorize")
	public AuthenticationToken login(@RequestBody AuthenticationRequest authenticationRequest, HttpSession session) {
		String userEmail = authenticationRequest.getUserEmail();
		String password = authenticationRequest.getPassword();
		
		log.info(authenticationRequest.toString());
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userEmail, password);
		
		log.info(token.toString());
		Authentication authentication = authenticationManager.authenticate(token);
		
		log.info("------------넘어가나?-------------");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
				SecurityContextHolder.getContext());
		MemberAccount memberAccount = memberAuthService.readMemberAccount(userEmail);
		return new AuthenticationToken(memberAccount.getMemberId(),memberAccount.getEmail(), memberAccount.getAuthorities(), session.getId());
	}
}
