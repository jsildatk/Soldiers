package org.soldiers.application.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class LoginTests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void givenAdminOnLogin_shouldSucceedWithRedirectToLoginSuccess() throws Exception {
        mvc.perform(formLogin().user("admin").password("zaq1@WSX"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/loginSuccess"))
                .andExpect(authenticated().withAuthenticationName("admin"));
    }

    @Test
    public void givenInvalidUserOnLogin_shouldFailWithRedirectToLoginError() throws Exception {
        mvc.perform(formLogin().user("invalidUsername").password("invalidPassword"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/loginError"))
                .andExpect(unauthenticated());
    }

    @Test
    public void givenLogoutRequest_shouldSucceedWithRedirectToHome() throws Exception {
        mvc.perform(logout())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"));
    }
}
