package org.soldiers.application.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AuthenticationTests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void givenRequestOnHomeWithAnyUser_shouldSucceedWith200() throws Exception {
        mvc.perform(get("/").contentType(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void givenAuthRequestOnAdminWithAdmin_shouldSucceedWith200() throws Exception {
        mvc.perform(get("/admin").contentType(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = { "SOLDIER", "COMMANDER" })
    public void givenAuthRequestOnAdminWithSoldierOrCommander_shouldReturn403() throws Exception {
        mvc.perform(get("/admin").contentType(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().is(403))
                .andExpect(forwardedUrl("/403"));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void givenAuthRequestOnSoldierOrCommanderWithAdmin_shouldReturn403() throws Exception {
        mvc.perform(get("/soldier").contentType(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().is(403))
                .andExpect(forwardedUrl("/403"));

        mvc.perform(get("/commander").contentType(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().is(403))
                .andExpect(forwardedUrl("/403"));
    }

    @Test
    public void givenAuthRequestOnAnyUserWithoutAuth_shouldReturn302() throws Exception {
        mvc.perform(get("/admin").contentType(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("http://localhost/"));

        mvc.perform(get("/commander").contentType(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("http://localhost/"));

        mvc.perform(get("/soldier").contentType(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("http://localhost/"));
    }

    @Test
    @WithMockUser(username = "soldier_test", authorities = { "COMMANDER", "SOLDIER" })
    public void givenAuthRequestOnSettingsOrPersonalDataOrEquipmentWithCommanderOrSolider_shouldSucceedWith200() throws Exception {
        mvc.perform(get("/settings").contentType(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().isOk());

        mvc.perform(get("/personalData").contentType(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().isOk());

        mvc.perform(get("/equipment").contentType(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
