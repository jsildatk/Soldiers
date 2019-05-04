package org.soldiers.application.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.soldiers.model.*;
import org.soldiers.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.lang.Math.toIntExact;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class GetTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private SoldierRepository soldierRepository;

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @WithMockUser(authorities = { "ADMIN", "COMMANDER", "SOLDIER" })
    public void givenNewsId_whenGetNewsById_thenStatus200() throws Exception {
        Long id = 1L;
        News n = newsRepository.findById(id).get();
        mvc.perform(get("/news/rest/" + id).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is(n.getTitle())));
    }

    @Test
    @WithMockUser(authorities = { "ADMIN", "COMMANDER", "SOLDIER" })
    public void givenNewsTitle_whenGetNewsByTitle_thenStatus200() throws Exception {
        String title = "Przerwa techniczna";
        News n = newsRepository.findByTitle(title);
        mvc.perform(get("/news/rest/title/" + title).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is(n.getTitle())));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void givenTeamId_whenGetTeamById_thenStatus200() throws Exception {
        Long id = 2L;
        Team t = teamRepository.findById(id).get();
        mvc.perform(get("/admin/teams/" + id).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.team", is(t.getTeam())));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void givenTeamId_whenGetSoldiersByTeam_thenStatus200() throws Exception {
        Long id = 1L;
        List<Soldier> soldiers = soldierRepository.findByTeam(teamRepository.findById(id).get());
        mvc.perform(get("/admin/teams/soldiers/" + id).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[" + (id-1) + "].firstName", is(soldiers.get(toIntExact(id-1)).getFirstName())));
    }

    @Test
    @WithMockUser(authorities = { "ADMIN", "COMMANDER", "SOLDIER" })
    public void givenMissionMissions_whenGetMissionByMission_thenStatus200() throws Exception {
        String mission = "Burza";
        Mission m = missionRepository.findByMission(mission);
        mvc.perform(get("/missions/mission/" + mission).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.mission", is(m.getMission())));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void givenUserId_whenGetUserById_thenStatus200() throws Exception {
        Long id = 2L;
        User u = userRepository.findById(id).get();
        mvc.perform(get("/admin/users/" + id).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email", is(u.getEmail())));
    }
}
