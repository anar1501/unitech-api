package az.unibank.unitech.controller;


import az.unibank.unitech.data.dto.request.LoginRequestDto;
import az.unibank.unitech.data.dto.request.RegisterRequestDto;
import az.unibank.unitech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired(required = false)
    private UserService userService;

    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody RegisterRequestDto registerRequestDto) {
        userService.register(registerRequestDto);
        return "User successfully registred";
    }

    @PostMapping(value = "/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(userService.login(loginRequestDto));
    }

    @GetMapping(value = "/register-confirm")
    public HttpStatus registerConfirm(@RequestParam(value = "activationcode") String activationCode) {
        userService.registerConfirm(activationCode);
        return HttpStatus.OK;
    }
}
