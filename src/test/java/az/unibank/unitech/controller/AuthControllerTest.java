package az.unibank.unitech.controller;

import az.unibank.unitech.data.dto.request.LoginRequestDto;
import az.unibank.unitech.data.dto.request.RegisterRequestDto;
import az.unibank.unitech.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenRegisterRequestDtoObject_whenRegisterUser_thenReturnRegistredUser() throws Exception{
        RegisterRequestDto registerRequestDto = new RegisterRequestDto();
        registerRequestDto.setFin("ABC1234");
        registerRequestDto.setPassword("leavemealone 0");
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequestDto))).andReturn();
        int status=mvcResult.getResponse().getStatus();
        assertEquals(201,status);
        String content=mvcResult.getResponse().getContentAsString();
        assertEquals(content,"User successfully registred");
    }

    @Test
    public void givenLoginRequestDtoObject_whenRegisterUser_thenReturnRegistredUser() throws Exception{
        LoginRequestDto registerRequestDto = new LoginRequestDto();
        registerRequestDto.setFin("ABC1234");
        registerRequestDto.setPassword("leavemealone 0");
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequestDto))).andReturn();
        int status=mvcResult.getResponse().getStatus();
        assertEquals(200,status);
    }
}
