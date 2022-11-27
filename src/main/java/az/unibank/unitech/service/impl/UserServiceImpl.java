package az.unibank.unitech.service.impl;


import az.unibank.unitech.data.dto.request.LoginRequestDto;
import az.unibank.unitech.data.dto.request.RegisterRequestDto;
import az.unibank.unitech.data.dto.response.UserResponseDto;
import az.unibank.unitech.data.entity.User;
import az.unibank.unitech.data.repository.UserRepository;
import az.unibank.unitech.data.repository.UserStatusRepositry;
import az.unibank.unitech.enums.UserStatusEnum;
import az.unibank.unitech.exception.*;
import az.unibank.unitech.service.UserService;
import az.unibank.unitech.utils.DateUtils;
import az.unibank.unitech.utils.MessageUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false) private  UserRepository userRepository;

    @Autowired(required = false) private  UserStatusRepositry userStatusRepositry;
    private final static Date currentDate = new Date();
    @Autowired(required = false) private MessageUtils messageUtils;

    private final static String messageSubject="Registration Confirm Link";

    private final static String messageBody="Please confirm your registration url: ";

    @Override
    public Optional<User> login(String fin,String password) {
        User user = userRepository.findByFin(fin).orElseThrow(FinNotFoundException::new);
        if (!password.equals(user.getPassword())) {
            throw new WrongPasswordException();
        }
        if (user.getStatus().getId().equals(UserStatusEnum.UNCONFIRMED.getStatusId())) {
            throw new UnconfirmedException();
        }
        return Optional.of(user);
    }

    @SneakyThrows
    @Override
    public UserResponseDto register(RegisterRequestDto registerRequestDto) {
        User user=new User();
        UserResponseDto returnValue=new UserResponseDto();
        if (userRepository.existsByFin(registerRequestDto.getFin())) throw new FinAlreadyTaken();
        user.setFin(registerRequestDto.getFin());
        user.setPassword(registerRequestDto.getPassword());
        user.setEmail(registerRequestDto.getEmail());
        user.setPassword(registerRequestDto.getPassword());
        user.setExpiredDate(DateUtils.prepareRegistrationExpirationDate());
        user.setActivationCode(UUID.randomUUID().toString());
        user.setStatus(userStatusRepositry.findUserStatusById(1L));
        User savedUser = userRepository.save(user);
        String confirmLink = "http://localhost:8080/api/v1/auth/register-confirm?activationcode=" + savedUser.getActivationCode();
        messageUtils.sendAsync(savedUser.getEmail(), messageSubject, messageBody + confirmLink);
        returnValue.setId(savedUser.getId());
        returnValue.setFin(savedUser.getFin());
        return returnValue;
    }

    @Transactional
    @Override
    public void registerConfirm(String activationCode) {
        User user = userRepository.findByActivationCode(activationCode);
        if (user.getStatus().getId().equals(UserStatusEnum.CONFIRMED.getStatusId())) {
            throw new AlreadyConfirmedException();
        }
        Date expiredDate = user.getExpiredDate();
        if (expiredDate.before(currentDate)) {
            throw new ExpirationCodeIsExpiredException();
        } else {
            user.setStatus(userStatusRepositry.findUserStatusById(UserStatusEnum.CONFIRMED.getStatusId()));
            userRepository.save(user);
        }
    }
}
