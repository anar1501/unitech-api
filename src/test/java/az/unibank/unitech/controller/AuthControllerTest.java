package az.unibank.unitech.controller;

import az.unibank.unitech.data.dto.request.LoginRequestDto;
import az.unibank.unitech.data.dto.request.RegisterRequestDto;
import az.unibank.unitech.data.dto.response.UserResponseDto;
import az.unibank.unitech.data.entity.User;
import az.unibank.unitech.data.repository.UserRepository;
import az.unibank.unitech.exception.FinNotFoundException;
import az.unibank.unitech.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private LoginRequestDto request = new LoginRequestDto();

    @BeforeEach
    void setup() {
        request.setPassword("saijdiasjdoiad");
        request.setFin("ksjadijsaida");
    }

    @Test
    void should_return_registered_user() throws Exception {
        RegisterRequestDto requestDto = new RegisterRequestDto();
        requestDto.setPassword("123456");
        requestDto.setFin("ABC1234");
        requestDto.setEmail("test@gmail.com");
        UserResponseDto user = new UserResponseDto();
        user.setFin(requestDto.getFin());
        when(userService.register(ArgumentMatchers.any(RegisterRequestDto.class))).thenReturn(user);
        mockMvc.perform(post("/api/v1/auth/register")
                        .content(objectMapper.writeValueAsString(requestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.fin").value(requestDto.getFin()));
    }

    @Test
    void loginTest() throws Exception {
        User user = new User();
        user.setFin("ABC1234");
        user.setPassword("test123");
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setFin(user.getFin());
        loginRequestDto.setPassword(user.getPassword());
        given(userService.login(user.getFin(), user.getPassword())).willReturn(Optional.of(user));
        mockMvc.perform(post("/api/v1/auth/login")
                        .content(objectMapper.writeValueAsString(loginRequestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fin", is(user.getFin())));
    }

    @Test
    void whenUserNotExists_thenHttp404() throws Exception {
        Mockito.doReturn(Optional.empty())
                .when(userService)
                .login(request.getFin(), request.getFin());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login"))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    void whenUserExists_thenHttp200_andUserReturned() throws Exception {
        String fin = "ABC1234";
        User user = new User();
        user.setPassword("test123");
        user.setFin(fin);
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setFin(user.getFin());
        loginRequestDto.setPassword(user.getPassword());
        Mockito.doReturn(Optional.of(user))
                .when(userRepository)
                .findByFin(request.getFin());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login").content(objectMapper.writeValueAsString(loginRequestDto))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.content().string(containsString(fin)));
    }

    @Test
    void should_throw_exceotion_when_user_doesnt_exist()throws Exception{
        LoginRequestDto loginRequestDto=new LoginRequestDto();
        loginRequestDto.setPassword("asijhdisajd");
        loginRequestDto.setFin("aisjdiasjd");

        Mockito.doThrow(new FinNotFoundException()).when(userService.login(loginRequestDto.getFin(),loginRequestDto.getPassword()));
        mockMvc.perform(post("/api/v1/auth/login")
                .content(objectMapper.writeValueAsString(loginRequestDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }



}
