package az.unibank.unitech.service;


import az.unibank.unitech.data.dto.request.LoginRequestDto;
import az.unibank.unitech.data.dto.request.RegisterRequestDto;
import az.unibank.unitech.data.dto.response.UserResponseDto;
import az.unibank.unitech.data.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> login(String fin,String password);
    UserResponseDto register(RegisterRequestDto registerRequestDto);
    void registerConfirm(String activationCode);
}
