package org.prj.arachne.application;

import java.util.List;
import lombok.AllArgsConstructor;
import org.prj.arachne.application.exception.FailTOFCMPushMessageException;
import org.prj.arachne.application.exception.FailToDoItemServiceException;
import org.prj.arachne.application.exception.FailUpdateToDoItemServiceException;
import org.prj.arachne.domain.Schedule.ToDoItem;
import org.prj.arachne.domain.Schedule.ToDoItemRepository;
import org.prj.arachne.domain.member.MemberAccount;
import org.prj.arachne.domain.member.repository.MemberAccountRepository;
import org.prj.arachne.presentation.dto.ChangedDataType;
import org.prj.arachne.presentation.dto.FCMessageDTO;
import org.prj.arachne.presentation.dto.FCMessageDTO.Notification;
import org.prj.arachne.util.fcm.FCMUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ScheduleService {


    private ToDoItemRepository toDoItemRepository;

    private MemberAccountRepository accountRepository;

    private FCMUtil fcmUtil;

    //@Transactional
    public void registerToDoItem(ToDoItem toDoItem) {

        try {
            toDoItemRepository.save(toDoItem);

            pushChangedMessage(toDoItem.getItemOwner().getMemberId());

        } catch (Exception e) {
            e.printStackTrace();
            throw new FailToDoItemServiceException("TODOITem 등록에 실패했습니다.....");
        }

    }

    private void pushChangedMessage(long memberId) {

        FCMessageDTO fcMessageDTO = new FCMessageDTO();
        MemberAccount memberAccount = accountRepository.findOne(memberId);
        fcMessageDTO.setTo(memberAccount.getFcmRedirectToken());
        Notification notification = new Notification();
        notification.setTitle("mirrorSettingData");
        notification.setBody(ChangedDataType.TODOLIST.toString());

        fcMessageDTO.setNotification(notification);

        int isSuccess = fcmUtil.pushMessage(fcMessageDTO);

        if (isSuccess != 1) {
            throw new FailTOFCMPushMessageException("fcm 푸시 실패했습니다");
        }
    }

    public List<ToDoItem> requestToDOList(Long memberSerialNum){

        List<ToDoItem> toDoItemList= null;
        try {
            toDoItemList = toDoItemRepository.findByItemOwnerMemberIdOrderByDateAsc(memberSerialNum);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FailToDoItemServiceException("ToDoItem 리스트 목록 요청에 실패했습니다. ");
        }

        return toDoItemList;


    }

    @Transactional
    public void modifyToDoItem(ToDoItem toDoItem){

        if (toDoItemRepository.exists(toDoItem.getId())) {

            toDoItemRepository.save(toDoItem);

            pushChangedMessage(toDoItem.getItemOwner().getMemberId());

        }else {
            throw new FailUpdateToDoItemServiceException("해당 TOdoItem이 존재하지 않습니다.....");
        }

    }


    public void deleteToDoItemList(Long id){

        try {
            toDoItemRepository.delete(id);


        } catch (Exception e) {
            e.printStackTrace();
            throw new FailToDoItemServiceException("TODOItemS 삭제에 실패했습니다.....");
        }


    }





}
