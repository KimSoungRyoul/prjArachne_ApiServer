package org.prj.arachne.model.member.repository;

import org.prj.arachne.model.member.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberInfoRepository extends JpaRepository<MemberInfo, Long>{
	
	public MemberInfo findByInfoOwnerEmail(String userEmail);

}
