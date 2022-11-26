package az.unibank.unitech.mapper;

import az.unibank.unitech.data.dto.request.CreateAccountRequestDto;
import az.unibank.unitech.data.dto.request.TransferRequestDto;
import az.unibank.unitech.data.dto.response.AccountResponseDto;
import az.unibank.unitech.data.entity.Account;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",builder = @Builder(disableBuilder = true),imports = {Object.class})
public interface AccountMapStruct {
    List<AccountResponseDto>map(List<Account> accounts);
    AccountResponseDto map(Account account);
    @Mapping(target = "balance",source = "amount")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "price", ignore = true)
    Account map(CreateAccountRequestDto createAccountRequestDto);

    @Mapping(target = "user.id",source = "userId")
    @Mapping(target = "balance",source = "amount")
    @Mapping(target = "id",ignore = true)
    Account map(TransferRequestDto transferRequestDto);
}
