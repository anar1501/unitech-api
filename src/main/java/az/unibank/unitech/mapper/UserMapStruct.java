package az.unibank.unitech.mapper;


import az.unibank.unitech.data.dto.request.LoginRequestDto;
import az.unibank.unitech.data.dto.request.RegisterRequestDto;
import az.unibank.unitech.data.dto.response.UserResponseDto;
import az.unibank.unitech.data.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",builder = @Builder(disableBuilder = true),imports = {Object.class},injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapStruct {
    @Mapping(target = "fin",source = "fin")
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "status",ignore = true)
    @Mapping(target = "createdDate",ignore = true)
    @Mapping(target = "activationCode",ignore = true)
    @Mapping(target = "expiredDate",ignore = true)
    @Mapping(target = "email",ignore = true)
    User map(LoginRequestDto user);
}
