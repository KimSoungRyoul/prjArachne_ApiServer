package org.prj.arachne.presentation.dto;

import java.util.Collection;
import org.prj.arachne.domain.member.MemberAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationToken {
    
	 private Long memberSerialNum;
     private String userEmail;
     private Collection<MemberAuthority> authorities;
     private String token;
    
    
  }

