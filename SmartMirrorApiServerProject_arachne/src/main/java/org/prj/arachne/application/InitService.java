package org.prj.arachne.application;

import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.prj.arachne.domain.Schedule.ToDoItem;
import org.prj.arachne.domain.fileinfo.FileInfo;
import org.prj.arachne.domain.fileinfo.repository.FileInfoRepository;
import org.prj.arachne.domain.fileinfo.valueObj.FileInfoId;
import org.prj.arachne.domain.fileinfo.valueObj.FileType;
import org.prj.arachne.domain.fileinfo.valueObj.OwnerType;
import org.prj.arachne.domain.fileinfo.valueObj.SaveStatus;
import org.prj.arachne.domain.member.MemberAccount;
import org.prj.arachne.domain.member.MemberAuthority;
import org.prj.arachne.domain.member.MemberInfo;
import org.prj.arachne.domain.member.MemberMirrorSettingInfo;
import org.prj.arachne.domain.member.repository.MemberAccountRepository;
import org.prj.arachne.domain.member.repository.MemberAuthorityRepository;
import org.prj.arachne.domain.member.repository.MemberInfoRepository;
import org.prj.arachne.domain.member.repository.MemberMirrorSettingInfoRepository;
import org.prj.arachne.domain.member.valueObj.AuthorityType;
import org.prj.arachne.domain.member.valueObj.Gender;
import org.prj.arachne.domain.member.valueObj.Password;
import org.prj.arachne.domain.member.valueObj.PhysicalInfo;
import org.prj.arachne.domain.member.valueObj.PhysicalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;



@Service
@Log4j
@Profile({"dev","unitTest"})
public class InitService {

	
	MemberAccountRepository mRepo;
	
	MemberAuthorityRepository mAuthRepo;
	
	MemberInfoRepository mInfoRepo;
	
	PasswordEncoder passwordEncoder;

	FileInfoRepository fInfoRepository;

	MemberMirrorSettingInfoRepository memberMirrorSettingInfoRepository;

	ScheduleService scheduleService;

	MemberInfoService memberInfoService;


	@Autowired
	public InitService(MemberAccountRepository mRepo, MemberAuthorityRepository mAuthRepo,
			MemberInfoRepository mInfoRepo, PasswordEncoder passwordEncoder, FileInfoRepository fInfoRepository,
					   ScheduleService scheduleService,MemberInfoService memberInfoService,MemberMirrorSettingInfoRepository memberMirrorSettingInfoRepository
					   ) {
		super();
		this.mRepo = mRepo;
		this.mAuthRepo = mAuthRepo;
		this.mInfoRepo = mInfoRepo;
		this.passwordEncoder = passwordEncoder;
		this.fInfoRepository = fInfoRepository;
		this.scheduleService = scheduleService;
		this.memberInfoService = memberInfoService;
		this.memberMirrorSettingInfoRepository = memberMirrorSettingInfoRepository;
	}







	@PostConstruct
	@Transactional
	public void init() {
		
		createUser1();
		
		createUser2();


		createToDoItem();
			
		
	}

	private void createToDoItem(){


		ToDoItem toDoItem=new ToDoItem(null,null,new Date(),"testTitle","conte가나다라마바사fasdasfasd");

		MemberAccount memberAccount=new MemberAccount(1L);

		toDoItem.setItemOwner(memberAccount);

		scheduleService.registerToDoItem(toDoItem);

	}


	private void createUser1() {

		MemberAccount mAcc=new MemberAccount();
		mAcc.setMemberId(1L);
		mAcc.setEmail("KimSoungRyoul@gmail.com");
		mAcc.setPassword(new Password(this.passwordEncoder.encode("12345")));
		mAcc.setAccountNonLocked(true);
		//mAcc.setMInfo(mInfo);
		mAcc.setJoinDate(new Date());
		
		log.info("init  회원 계정 정보 : "+mAcc.getUsername());
		
		
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
		
		
		
		Set<MemberAuthority> authorities=new HashSet<>();
		authorities.add(mAuth);
		mAcc.setAuthorities(authorities);
		
		mRepo.save(mAcc);
		
		mInfo.setInfoOwner(mAcc);
		mInfoRepo.save(mInfo);
		
		mAuth.setAuthOwner(mAcc);
		mAuthRepo.save(mAuth);
		
		String fileLocation=new StringBuilder()
								.append(File.separator)
								.append(mAcc.getEmail())
								.append(File.separator)
								.append("스토커 (feat  크루셜스타)_매드 클라운_표독.mp3")
								.toString();
		
		FileInfo fInfo=new FileInfo(null,new FileInfoId(mAcc, "stalker")
										,fileLocation, new Date(), FileType.MP3, SaveStatus.HAS_OWNER,OwnerType.MEMBEROWN);		
		
		fInfoRepository.save(fInfo);
		
		
		fInfo.getFileSerialInfo().setMAccount(mAcc);
		
		fInfoRepository.save(fInfo);

		MemberMirrorSettingInfo settingInfo=new MemberMirrorSettingInfo(null,null,
				0,0,0,0,0,0);
		memberMirrorSettingInfoRepository.save(settingInfo);
		settingInfo.setSettingOwner(mAcc);
		memberMirrorSettingInfoRepository.save(settingInfo);

	}
	

	public void createUser2() {

		MemberAccount mAcc=new MemberAccount();

		mAcc.setMemberId(2L);
		mAcc.setEmail("rlatjduf510@naver.com");
		mAcc.setPassword(new Password(this.passwordEncoder.encode("12345")));
		mAcc.setAccountNonLocked(true);
		mAcc.setJoinDate(new Date());
		//mAcc.setMInfo(mInfo);
		
		log.info("init  회원 계정 정보 : "+mAcc.toString());
		mRepo.save(mAcc);
		
		MemberInfo mInfo=new MemberInfo();
		mInfo.setGender(Gender.WOMAN);
		mInfo.setName("권송");
		mInfo.setPhoneNum("010-7237-6602");
		mInfo.setPhysicalInfo(new PhysicalInfo(163, 55, PhysicalType.SLIM));
		mInfo.setInfoOwner(mAcc);
		mInfoRepo.save(mInfo);
		
		mInfo.setInfoOwner(mAcc);
		mInfoRepo.save(mInfo);
		
		MemberAuthority mAuth=new MemberAuthority();
		mAuth.setGrantedDate(new Date());
		mAuth.setAuthorityType(AuthorityType.NORMAL_USER);
		mAuth.setAuthOwner(mAcc);
		mAuthRepo.save(mAuth);
		
		
		Set<MemberAuthority> authorities=new HashSet<>();
		authorities.add(mAuth);
		mAcc.setAuthorities(authorities);

		mAcc.setMInfo(mInfo);
		
		
		
		
		mAuth.setAuthOwner(mAcc);
		mAuthRepo.save(mAuth);

		//memberInfoService.signUpMember(mAcc);
		MemberMirrorSettingInfo settingInfo=new MemberMirrorSettingInfo(null,null,
				0,0,0,0,0,0);
		memberMirrorSettingInfoRepository.save(settingInfo);
		settingInfo.setSettingOwner(mAcc);
		memberMirrorSettingInfoRepository.save(settingInfo);
			
	}
	
	
	
	
	
	
	
	
	
	
}
