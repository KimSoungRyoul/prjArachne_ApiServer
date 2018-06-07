package org.prj.arachne.domain.member.repository;

import org.prj.arachne.domain.member.MemberAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberAccountRepository extends JpaRepository<MemberAccount, Long>{

  MemberAccount findByEmail(String email);

}
