package org.soldiers.application;

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
public class MvcTests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void givenURIToHomePage_shouldRenderHomeView() throws Exception {
        mvc.perform(get("/").contentType(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user", "soldiers"))
                .andExpect(model().hasNoErrors())
                .andExpect(model().size(2))
                .andExpect(view().name("index"));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void givenURIToAdminHomePage_shouldRenderAdminHomeView() throws Exception {
        mvc.perform(get("/admin").contentType(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(model().size(2))
                .andExpect(view().name("admin/index"));
    }

    @Test
    @WithMockUser(authorities = "COMMANDER")
    public void givenURIToCommanderNewsPage_shouldRenderCommanderNewsPage() throws Exception {
        mvc.perform(get("/commander/news").contentType(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(model().size(3))
                .andExpect(view().name("commander/news"));
    }

    @Test
    public void givenURITo403_shouldRender403View() throws Exception {
        mvc.perform(get("/403").contentType(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("error/403"));
    }
}
