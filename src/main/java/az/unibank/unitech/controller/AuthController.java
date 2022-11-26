package az.unibank.unitech.controller;


import az.unibank.unitech.data.dto.request.LoginRequestDto;
import az.unibank.unitech.data.dto.request.RegisterRequestDto;
import az.unibank.unitech.data.dto.response.UserResponseDto;
import az.unibank.unitech.data.entity.User;
import az.unibank.unitech.exception.UserNotFoundException;
import az.unibank.unitech.mapper.UserMapStruct;
import az.unibank.unitech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired(required = false)
    private UserService userService;

    @Autowired(required = false)
    private UserMapStruct userMapStruct;

    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto register(@RequestBody RegisterRequestDto registerRequestDto) {
        return userService.register(registerRequestDto);
    }

    @PostMapping(value = "/login")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
//        User mappedUser = userMapStruct.map(loginRequestDto);
        User mappedUser=new User();
        mappedUser.setPassword(loginRequestDto.getPassword());
        mappedUser.setFin(loginRequestDto.getFin());
        Optional<User> userOptional = userService.login(mappedUser.getFin(), mappedUser.getPassword());
        User user=userOptional.orElseThrow(UserNotFoundException::new);
        UserResponseDto userResponseDto=new UserResponseDto();
        userResponseDto.setFin(user.getFin());
        return userResponseDto;
    }

    @GetMapping(value = "/register-confirm")
    public HttpStatus registerConfirm(@RequestParam(value = "activationcode") String activationCode) {
        userService.registerConfirm(activationCode);
        return HttpStatus.OK;
    }
}
