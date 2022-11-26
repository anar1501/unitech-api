package az.unibank.unitech.service;


import az.unibank.unitech.data.dto.request.LoginRequestDto;
import az.unibank.unitech.data.dto.request.RegisterRequestDto;
import az.unibank.unitech.data.dto.response.UserResponseDto;
import az.unibank.unitech.data.entity.User;
import az.unibank.unitech.data.entity.UserStatus;
import az.unibank.unitech.data.repository.UserRepository;
import az.unibank.unitech.data.repository.UserStatusRepositry;
import az.unibank.unitech.exception.FinNotFoundException;
import az.unibank.unitech.service.impl.UserServiceImpl;
import az.unibank.unitech.utils.MessageUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class UserServiceTests {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserStatusRepositry userStatusRepositry;

    @Mock
    private MessageUtils messageUtils;

    private final User user = new User();
    private final UserStatus status = new UserStatus();
    private final RegisterRequestDto registerRequestDto = new RegisterRequestDto();
    private final LoginRequestDto loginRequestDto = new LoginRequestDto();

    @BeforeEach
    void setupUser() {
        user.setId(1L);
        user.setFin("ABC1234");
        user.setEmail("test@gmail.com");
        user.setCreatedDate(new Date());
        user.setPassword("test12345");
    }
    @BeforeEach
    void setupUserStatus() {
        status.setId(1L);
        status.setName("UNCONFIRMED");
    }


    @BeforeEach
    void setupRegisterRequestDto() {
        registerRequestDto.setFin("ABC1234");
        registerRequestDto.setPassword("test12345");
        registerRequestDto.setEmail("test@gmail.com");
    }

    @BeforeEach
    void setupLoginRequestDto() {
        loginRequestDto.setFin("CBA1234");
        loginRequestDto.setPassword("JASUHDJSAHDJAS");
    }

    @Test
    void should_register_user() {
        when(userRepository.save(any(User.class))).thenReturn(user);
        UserResponseDto actual = userService.register(registerRequestDto);
        assertThat(actual).usingRecursiveComparison().isEqualTo(user);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void should_not_found_a_user_that_doesnt_exist() {
        when(userRepository.findByFin(anyString())).thenReturn(Optional.empty());
        org.junit.jupiter.api.Assertions.assertThrows(FinNotFoundException.class, () -> userService.login(loginRequestDto));
        verify(userRepository,times(1)).findByFin(anyString());
    }
}
