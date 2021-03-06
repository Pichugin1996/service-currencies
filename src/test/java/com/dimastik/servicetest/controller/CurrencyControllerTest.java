package com.dimastik.servicetest.controller;

import com.dimastik.service.Application;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Проверка ответа")
    public void getResponseApi() throws Exception {
        mockMvc.perform(get("/api/"))
                .andExpect(status().isFound());
    }

    @Test
    @DisplayName("Проверка ответа c параметром")
    public void getResponseParam() throws Exception {
        mockMvc.perform(get("/api/RUB"))
                .andExpect(status().isFound());
    }

    @Test
    @DisplayName("Проверка ответа c некорректным параметром")
    public void getResponseBadParam() throws Exception {
        mockMvc.perform(get("/api/BadParam"))
                .andExpect(status().isFound());
    }

}
