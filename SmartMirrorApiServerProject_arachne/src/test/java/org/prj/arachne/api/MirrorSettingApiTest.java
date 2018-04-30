/*
package org.prj.arachne.api;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.prj.arachne.domain.member.MemberMirrorSettingInfo;
import org.prj.arachne.presentation.api.MirrorSettingInfoApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;





@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("unitTest")
public class MirrorSettingApiTest {


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MemberMirrorSettingInfo memberMirrorSettingInfo;

    @Before
    public void setUp() throws  Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        memberMirrorSettingInfo=new MemberMirrorSettingInfo(null,null,
                0,0,0,0,0,0);
    }
    @Test
    public void testCreate() throws Exception {

        this.mockMvc.perform(get("api/v1/mirrorsetting/1"))
                // 처리 내용을 출력합니다.
                .andDo(print())
                // 상태값은 OK가 나와야 합니다.
                .andExpect(status().isOk());


    }







}
*/
