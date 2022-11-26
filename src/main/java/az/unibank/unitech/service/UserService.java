package az.unibank.unitech.service;


import az.unibank.unitech.data.dto.request.LoginRequestDto;
import az.unibank.unitech.data.dto.request.RegisterRequestDto;
import az.unibank.unitech.data.dto.response.UserResponseDto;

public interface UserService {
    String login(LoginRequestDto loginRequestDto);
    UserResponseDto register(RegisterRequestDto registerRequestDto);
    void registerConfirm(String activationCode);
}
