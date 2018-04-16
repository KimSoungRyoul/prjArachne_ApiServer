package org.prj.arachne.api.contents;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.prj.arachne.application.ScheduleService;
import org.prj.arachne.domain.Schedule.ToDoItem;
import org.prj.arachne.domain.member.MemberAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j
@ActiveProfiles("dev")
@Transactional
public class ScheduleServiceTest {

    @Autowired
    private ScheduleService scheduleService;



    @Test
    public void testRegister(){



        ToDoItem toDoItem=new ToDoItem(null,null,new Date(),"testTitle","contesafdasdfasdfasdasfasd");

        MemberAccount memberAccount=new MemberAccount(1L);

        toDoItem.setItemOwner(memberAccount);

        scheduleService.registerToDoItem(toDoItem);

    }

    @Test
    public void testRequest(){

        List<ToDoItem> toDoItemList=scheduleService.requestToDOList(1L);

        Assert.assertNotNull(toDoItemList);



    }


}
