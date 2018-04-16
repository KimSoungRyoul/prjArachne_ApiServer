package org.prj.arachne.application;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.prj.arachne.application.exception.UnSignedMemberException;
import org.prj.arachne.domain.member.MemberAccount;
import org.prj.arachne.domain.member.MemberAuthority;
import org.prj.arachne.domain.member.MemberInfo;
import org.prj.arachne.domain.member.repository.MemberAccountRepository;
import org.prj.arachne.domain.member.repository.MemberAuthorityRepository;
import org.prj.arachne.domain.member.repository.MemberInfoRepository;
import org.prj.arachne.domain.member.valueObj.AuthorityType;
import org.prj.arachne.domain.member.valueObj.Password;
import org.prj.arachne.util.mail.MailSenderUtil;
import org.prj.arachne.util.mail.dto.MailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberInfoService {

	private MemberInfoRepository mInfoRepo;

	private MemberAccountRepository mRepo;

	private MemberAuthorityRepository mAuthRepo;

	private PasswordEncoder passwordEncoder;

	private MailSenderUtil mailSender;
	
	
	@Autowired
	public MemberInfoService(MemberInfoRepository mInfoRepo, MemberAccountRepository mRepo,
			MemberAuthorityRepository mAuthRepo, PasswordEncoder passwordEncoder, MailSenderUtil mailSender) {
		super();
		this.mInfoRepo = mInfoRepo;
		this.mRepo = mRepo;
		this.mAuthRepo = mAuthRepo;
		this.passwordEncoder = passwordEncoder;
		this.mailSender = mailSender;
	}

	@PreAuthorize("(#memberSerialNum == principal.memberId) and hasAuthority('NORMAL_USER')")
	public MemberInfo requestMemberInfo(Long memberSerialNum) {

		MemberInfo mInfo = mInfoRepo.findOne(memberSerialNum);

		return mInfo;
	}

	@Transactional
	public void signUpMember(MemberAccount newMember) {
		// TODO Auto-generated method stub
		newMember.getPasswordVO().encryptValue(passwordEncoder);

		MemberInfo mInfo = newMember.getMInfo();

		MemberAuthority mAuth = new MemberAuthority(null, new Date(), AuthorityType.NORMAL_USER, null);

		Set<MemberAuthority> mAuths = new HashSet<>();

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
		
		
		//  가입 메일 발송
		mailSender.sendWithHTML(new MailDTO("Arachne 회원가입을 환영합니다", null, "arachne0823@gmail.com",
									newMember.getEmail(), null));

	}

	
	public void changedAccountInfo(Long memberId,String changedEmail,String changedPassword) throws UnSignedMemberException{

		
		MemberAccount modifiedMember=mRepo.findOne(memberId);
		
		if( modifiedMember==null) {
			
			throw new UnSignedMemberException("존재하지 않는 회원입니다. 회원의 계정정보수정이 불가능합니다..");
			
		}else {
			if(changedPassword!=null) {
				modifiedMember.setPassword(new Password(changedPassword).encryptValue(passwordEncoder));
			}
			if(changedEmail!=null) {
				modifiedMember.setEmail(changedEmail);
			}
			mRepo.save(modifiedMember);
			
		}
		
		
	}
	
	
	@Transactional
	public void modifiedMInfo(MemberInfo mInfo)throws UnSignedMemberException {
		
		MemberInfo exisitedMemberInfo=mInfoRepo.findOne(mInfo.getInfoOwner().getMemberId());
		
		if(exisitedMemberInfo==null) {
			
			throw new UnSignedMemberException("존재하지 않는 회원입니다. 회원의 개인정보수정이 불가능합니다..");
			
		}else {
			mInfo.setInfoOwner(exisitedMemberInfo.getInfoOwner());
			mInfo.setMInfoId(exisitedMemberInfo.getMInfoId());
			mInfoRepo.save(mInfo);
			
		}
		

	}

}
