package org.prj.arachne.model.member.repository;

import org.prj.arachne.model.member.MemberMirrorSettingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMirrorSettingInfoRepository extends JpaRepository<MemberMirrorSettingInfo,Long> {

    MemberMirrorSettingInfo findBySettingOwnerMemberId(Long memberId);
}
