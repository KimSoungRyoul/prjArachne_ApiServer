package org.prj.arachne.application;

import org.prj.arachne.domain.member.MemberAccount;
import org.prj.arachne.domain.member.repository.MemberAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;


@Service
@Log4j
public class MemberAuthenticationService implements UserDetailsService{
	
	@Autowired
	private MemberAccountRepository memberAccountRepository;
	
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		MemberAccount userDetails=memberAccountRepository.findByEmail(username);
		
		log.info("loadUserByUsername 메서드 호출 ----------------");
		//log.info(userDetails.getAuthorities());
		
		return userDetails;
	}

	public MemberAccount readMemberAccount(String userEmail) {
		
		MemberAccount memberAccount=memberAccountRepository.findByEmail(userEmail);
		
		
		return memberAccount;
	}

}
