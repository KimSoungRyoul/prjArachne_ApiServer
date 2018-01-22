package org.prj.arachne.application;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.prj.arachne.domain.member.MemberAccount;
import org.prj.arachne.domain.member.MemberAuthority;
import org.prj.arachne.domain.member.MemberInfo;
import org.prj.arachne.domain.member.repository.MemberAccountRepository;
import org.prj.arachne.domain.member.repository.MemberAuthorityRepository;
import org.prj.arachne.domain.member.repository.MemberInfoRepository;
import org.prj.arachne.domain.member.valueObj.AuthorityType;
import org.prj.arachne.domain.member.valueObj.Gender;
import org.prj.arachne.domain.member.valueObj.Password;
import org.prj.arachne.domain.member.valueObj.PhysicalInfo;
import org.prj.arachne.domain.member.valueObj.PhysicalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;



@Service
@Log4j
public class InitService {

	
	MemberAccountRepository mRepo;
	
	MemberAuthorityRepository mAuthRepo;
	
	MemberInfoRepository mInfoRepo;
	
	PasswordEncoder passwordEncoder;

	
	@Autowired
	public InitService(MemberAccountRepository mRepo, MemberAuthorityRepository mAuthRepo,
			MemberInfoRepository mInfoRepo, PasswordEncoder passwordEncoder) {
		super();
		this.mRepo = mRepo;
		this.mAuthRepo = mAuthRepo;
		this.mInfoRepo = mInfoRepo;
		this.passwordEncoder = passwordEncoder;
	}



	@PostConstruct
	public void init() {
		
		MemberInfo mInfo=new MemberInfo();
		mInfo.setGender(Gender.MAN);
		mInfo.setName("김성렬");
		mInfo.setPhoneNum("010-7237-6602");
		mInfo.setPhysicalInfo(new PhysicalInfo(181, 64, PhysicalType.SLIM));
	
		mInfoRepo.save(mInfo);
		
		
		MemberAuthority mAuth=new MemberAuthority();
		mAuth.setGrantedDate(new Date());
		mAuth.setAuthorityType(AuthorityType.NORMAL_USER);
		
		mAuthRepo.save(mAuth);
		
		MemberAccount mAcc=new MemberAccount();
		
		mAcc.setEmail("KimSoungRyoul@gmail.com");
		mAcc.setPassword(new Password(this.passwordEncoder.encode("12345")));
		mAcc.setAccountNonLocked(true);
		mAcc.setMInfo(mInfo);
		
		log.info("init  회원 계정 정보 : "+mAcc.toString());
		
		
		Set<MemberAuthority> authorities=new HashSet<>();
		authorities.add(mAuth);
		mAcc.setAuthorities(authorities);
		
		mRepo.save(mAcc);
		
		mAuth.setAuthOwner(mAcc);
		mAuthRepo.save(mAuth);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}