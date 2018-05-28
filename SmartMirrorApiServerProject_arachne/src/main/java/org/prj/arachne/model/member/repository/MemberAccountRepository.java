package org.prj.arachne.model.member.repository;

import org.prj.arachne.model.member.MemberAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberAccountRepository extends JpaRepository<MemberAccount, Long>{

	public MemberAccount findByEmail(String email);
	
}
