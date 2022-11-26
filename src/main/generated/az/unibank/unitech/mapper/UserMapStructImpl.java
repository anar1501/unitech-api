package az.unibank.unitech.mapper;

import az.unibank.unitech.data.dto.request.LoginRequestDto;
import az.unibank.unitech.data.dto.request.RegisterRequestDto;
import az.unibank.unitech.data.dto.response.UserResponseDto;
import az.unibank.unitech.data.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-27T02:23:17+0400",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 16.0.2 (Amazon.com Inc.)"
)
@Component
public class UserMapStructImpl implements UserMapStruct {

    @Override
    public User toEntity(RegisterRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setFin( dto.getFin() );
        user.setPassword( dto.getPassword() );
        user.setEmail( dto.getEmail() );

        return user;
    }

    @Override
    public List<UserResponseDto> map(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserResponseDto> list = new ArrayList<UserResponseDto>( users.size() );
        for ( User user : users ) {
            list.add( map( user ) );
        }

        return list;
    }

    @Override
    public UserResponseDto map(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setFin( user.getFin() );
        userResponseDto.setId( user.getId() );

        return userResponseDto;
    }

    @Override
    public RegisterRequestDto mapToRegisterRequestDto(User user) {
        if ( user == null ) {
            return null;
        }

        RegisterRequestDto registerRequestDto = new RegisterRequestDto();

        registerRequestDto.setFin( user.getFin() );
        registerRequestDto.setPassword( user.getPassword() );
        registerRequestDto.setEmail( user.getEmail() );

        return registerRequestDto;
    }

    @Override
    public LoginRequestDto prepareLoginRequestDto(User user) {
        if ( user == null ) {
            return null;
        }

        LoginRequestDto loginRequestDto = new LoginRequestDto();

        loginRequestDto.setFin( user.getFin() );
        loginRequestDto.setPassword( user.getPassword() );

        return loginRequestDto;
    }

    @Override
    public User map(LoginRequestDto user) {
        if ( user == null ) {
            return null;
        }

        User user1 = new User();

        user1.setFin( user.getFin() );
        user1.setPassword( user.getPassword() );

        return user1;
    }
}
