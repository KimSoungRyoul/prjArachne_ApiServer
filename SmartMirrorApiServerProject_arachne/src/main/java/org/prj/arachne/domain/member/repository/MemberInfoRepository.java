package org.prj.arachne.domain.member.repository;

import org.prj.arachne.domain.member.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberInfoRepository extends JpaRepository<MemberInfo, Long>{
	
	public MemberInfo findByInfoOwnerEmail(String userEmail);

}
