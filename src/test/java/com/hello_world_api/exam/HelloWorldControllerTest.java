package com.hello_world_api.exam;


import com.hello_world_api.exam.controller.HelloWorldController;
import com.hello_world_api.exam.service.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;



import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HelloWorldController.class)
class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HelloWorldService service;

    @Test
    void testValidFirstHalf() throws Exception {
        when(service.isFirstHalf('a')).thenReturn(true);

        mockMvc.perform(get("/hello-world?name=alice"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value("Hello Alice"));
    }

    @Test
    void testInvalidSecondHalf() throws Exception {
        when(service.isFirstHalf('z')).thenReturn(false);

        mockMvc.perform(get("/hello-world?name=zara"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    @Test
    void testMissingName() throws Exception {
        mockMvc.perform(get("/hello-world"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    @Test
    void testEmptyName() throws Exception {
        mockMvc.perform(get("/hello-world?name="))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error").value("Invalid Input"));
    }
}
