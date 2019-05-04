package org.soldiers.application.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.soldiers.model.Team;
import org.soldiers.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PostTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private TeamRepository teamRepository;

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void givenNewTeam_whenPost_thenStatus201() throws Exception {
        Team t = new Team("TeamName", null);
        mvc.perform(post("/admin/teams")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("team", t.getTeam()))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.team", is(t.getTeam())))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void givenWrongNamedTeam_whenPost_thenStatus200() throws Exception {
        Team t = new Team("notCapitalLetterTeam", null);
        mvc.perform(post("/admin/teams")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("team", t.getTeam()))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].defaultMessage", is("Pole 'grupa' musi się zaczynać z dużej litery oraz nie może zawierać cyfr")))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void givenExistingTeam_whenPost_thenStatus409() throws Exception {
        Team t = teamRepository.findById(1L).get();
        mvc.perform(post("/admin/teams")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("team", t.getTeam()))
                .andDo(print())
                .andExpect(status().isConflict());
    }
}
