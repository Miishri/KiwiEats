package org.delivery.KiwiEats.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void testAuthenticationFail() throws Exception {
        this.mockMvc.perform(get("/generate-token"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testAuthenticatedUser() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/generate-token")
                .with(httpBasic("Aam Wala", bCryptPasswordEncoder.encode("mangowala123"))))
                .andExpect(status().isOk())
                .andReturn();

        String jwtToken = mvcResult.getResponse().getContentAsString();

        this.mockMvc.perform(get("/kiwi/product")
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(jsonPath("$.size()", is(1)));
    }
}
