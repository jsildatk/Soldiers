package org.soldiers.application.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.soldiers.model.Address;
import org.soldiers.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static java.lang.Math.toIntExact;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PutTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void givenExistingAddressId_whenPut_thenStatus200() throws Exception {
        Long id = 1L;
        Address a = addressRepository.findById(id).get();
        a.setPostalCode("11-111");
        mvc.perform(put("/admin/addresses/" + id)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("street", a.getStreet())
                .param("city", a.getCity())
                .param("postalCode", a.getPostalCode()))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(toIntExact(a.getId()))))
                .andExpect(jsonPath("$.street", is(a.getStreet())))
                .andExpect(jsonPath("$.city", is(a.getCity())))
                .andExpect(jsonPath("$.postalCode", is(a.getPostalCode())))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void givenInvalidStreet_whenPut_thenStatus200() throws Exception {
        Long id = 2L;
        Address a = addressRepository.findById(id).get();
        a.setStreet("notValidStreet");
        mvc.perform(put("/admin/addresses/" + id)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("street", a.getStreet())
                .param("city", a.getCity())
                .param("postalCode", a.getPostalCode()))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].defaultMessage", is("Pole 'ulica' musi zaczynać się z dużej litery oraz mieć format ulica_numer lokalu")))
                .andExpect(status().isOk());
    }
}
