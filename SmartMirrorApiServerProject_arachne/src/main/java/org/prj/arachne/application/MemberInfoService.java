package org.prj.arachne.application;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.prj.arachne.domain.member.MemberAccount;
import org.prj.arachne.domain.member.MemberAuthority;
import org.prj.arachne.domain.member.MemberInfo;
import org.prj.arachne.domain.member.repository.MemberAccountRepository;
import org.prj.arachne.domain.member.repository.MemberAuthorityRepository;
import org.prj.arachne.domain.member.repository.MemberInfoRepository;
import org.prj.arachne.domain.member.valueObj.AuthorityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberInfoService {

	private MemberInfoRepository mInfoRepo;
	
	private MemberAccountRepository mRepo;
	
	private MemberAuthorityRepository mAuthRepo;
	
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public MemberInfoService(MemberInfoRepository mInfoRepo, MemberAccountRepository mRepo,
			MemberAuthorityRepository mAuthRepo,
			org.springframework.security.crypto.password.PasswordEncoder passwordEncoder) {
		super();
		this.mInfoRepo = mInfoRepo;
		this.mRepo = mRepo;
		this.mAuthRepo = mAuthRepo;
		this.passwordEncoder = passwordEncoder;
	}

	
	
	
	
	@PreAuthorize("(#memberSerialNum == principal.memberId) and hasAuthority('NORMAL_USER')")
	public MemberInfo requestMemberInfo(Long memberSerialNum) {
		
		MemberInfo mInfo= mInfoRepo.findOne(memberSerialNum);
		
		
		return mInfo;
	}

	

	@Transactional
	public void signUpMember(MemberAccount newMember) {
		// TODO Auto-generated method stub
		newMember.getPasswordVO().encryptValue(passwordEncoder);
		
		MemberInfo mInfo=newMember.getMInfo();
		
		MemberAuthority mAuth=new MemberAuthority(null, new Date(), AuthorityType.NORMAL_USER, null);
		
		
		Set<MemberAuthority> mAuths=new HashSet<>();
		
		
		mAuthRepo.save(mAuth);
		
		mRepo.save(newMember.excludedOtherEntity());
		
		mInfoRepo.save(mInfo);
		mInfo.setInfoOwner(newMember);
		mInfoRepo.save(mInfo);
		
		mAuth.setAuthOwner(newMember);
		mAuthRepo.save(mAuth);
		
		mAuths.add(mAuth);
		newMember.setAuthorities(mAuths);
		
		mRepo.save(newMember);
		
	}
	
	
	
}
