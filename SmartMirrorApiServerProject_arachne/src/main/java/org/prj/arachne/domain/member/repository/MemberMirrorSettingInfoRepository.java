package org.prj.arachne.domain.member.repository;

import org.prj.arachne.domain.member.MemberMirrorSettingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMirrorSettingInfoRepository extends JpaRepository<MemberMirrorSettingInfo,Long> {

    MemberMirrorSettingInfo findBySettingOwnerMemberId(Long memberId);
}
