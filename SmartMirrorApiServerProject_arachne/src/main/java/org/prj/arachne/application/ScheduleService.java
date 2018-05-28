package org.prj.arachne.application;

import lombok.AllArgsConstructor;
import org.prj.arachne.application.exception.FailToDoItemServiceException;
import org.prj.arachne.application.exception.FailUpdateToDoItemServiceException;
import org.prj.arachne.model.Schedule.ToDoItem;
import org.prj.arachne.model.Schedule.ToDoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ScheduleService {


    private ToDoItemRepository toDoItemRepository;

    //@Transactional
    public void registerToDoItem(ToDoItem toDoItem) {

        try {
            toDoItemRepository.save(toDoItem);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FailToDoItemServiceException("TODOITem 등록에 실패했습니다.....");
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

    public void modifyToDoItem(ToDoItem toDoItem){

       if(toDoItemRepository.exists(toDoItem.getId())){

           toDoItemRepository.save(toDoItem);

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
