package org.prj.arachne.application;

import lombok.AllArgsConstructor;
import org.prj.arachne.application.exception.FailTOFCMPushMessageException;
import org.prj.arachne.application.exception.UnSignedMemberException;
import org.prj.arachne.domain.member.MemberAccount;
import org.prj.arachne.domain.member.MemberMirrorSettingInfo;
import org.prj.arachne.domain.member.repository.MemberAccountRepository;
import org.prj.arachne.domain.member.repository.MemberMirrorSettingInfoRepository;
import org.prj.arachne.presentation.dto.ChangedDataType;
import org.prj.arachne.presentation.dto.FCMessageDTO;
import org.prj.arachne.presentation.dto.FCMessageDTO.Notification;
import org.prj.arachne.presentation.dto.MirrorSettingDTO;
import org.prj.arachne.util.fcm.FCMUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MirrorSettingService {

    private MemberMirrorSettingInfoRepository mirrorSettingInfoRepository;

    private MemberAccountRepository accountRepository;


    private FCMUtil fcmUtil;

    @Transactional
    @PreAuthorize("(#memberSerialNum == principal.memberId) and hasAuthority('NORMAL_USER')")
    public void modifyMirrorSetting(MirrorSettingDTO dto,Long memberSerialNum) {

        MemberMirrorSettingInfo mirrorSettingInfo = mirrorSettingInfoRepository.findBySettingOwnerMemberId(memberSerialNum);
        if (mirrorSettingInfo == null) {
            throw new UnSignedMemberException("해당 ID를 가지는 회원이 존재하지 않아 MirrorSetting을 수정할 수 없숩니다.");
        } else {
            //설정 정보 변경
            mirrorSettingInfo.modifyMirrorSetting(dto);

            mirrorSettingInfoRepository.save(mirrorSettingInfo);

            FCMessageDTO fcMessageDTO=new FCMessageDTO();
            MemberAccount memberAccount= accountRepository.findOne(memberSerialNum);



            fcMessageDTO.setTo(memberAccount.getFcmRedirectToken());
            Notification notification =new Notification();
            notification.setTitle("mirrorSettingData");
            notification.setBody(ChangedDataType.POS.toString());

            fcMessageDTO.setNotification(notification);

            int isSuccess= fcmUtil.pushMessage(fcMessageDTO);

            if(isSuccess!=1){
                throw new FailTOFCMPushMessageException("fcm 푸시 실패했습니다");
            }

        }

    }

    @PreAuthorize("(#memberId == principal.memberId) and hasAuthority('NORMAL_USER')")
    public MemberMirrorSettingInfo requestMirrorSetting(Long memberId) {

        MemberMirrorSettingInfo memberMirrorSettingInfo = mirrorSettingInfoRepository.findBySettingOwnerMemberId(memberId);


        return memberMirrorSettingInfo;
    }


}
