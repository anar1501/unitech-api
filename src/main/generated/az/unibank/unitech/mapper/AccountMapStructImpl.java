package az.unibank.unitech.mapper;

import az.unibank.unitech.data.dto.request.CreateAccountRequestDto;
import az.unibank.unitech.data.dto.request.TransferRequestDto;
import az.unibank.unitech.data.dto.response.AccountResponseDto;
import az.unibank.unitech.data.entity.Account;
import az.unibank.unitech.data.entity.User;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-26T23:29:01+0400",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 16.0.2 (Amazon.com Inc.)"
)
@Component
public class AccountMapStructImpl implements AccountMapStruct {

    @Override
    public List<AccountResponseDto> map(List<Account> accounts) {
        if ( accounts == null ) {
            return null;
        }

        List<AccountResponseDto> list = new ArrayList<AccountResponseDto>( accounts.size() );
        for ( Account account : accounts ) {
            list.add( map( account ) );
        }

        return list;
    }

    @Override
    public AccountResponseDto map(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountResponseDto accountResponseDto = new AccountResponseDto();

        accountResponseDto.setId( account.getId() );
        accountResponseDto.setBalance( account.getBalance() );
        accountResponseDto.setPrice( account.getPrice() );

        return accountResponseDto;
    }

    @Override
    public Account map(CreateAccountRequestDto createAccountRequestDto) {
        if ( createAccountRequestDto == null ) {
            return null;
        }

        Account account = new Account();

        account.setBalance( createAccountRequestDto.getAmount() );

        return account;
    }

    @Override
    public Account map(TransferRequestDto transferRequestDto) {
        if ( transferRequestDto == null ) {
            return null;
        }

        Account account = new Account();

        account.setUser( transferRequestDtoToUser( transferRequestDto ) );
        account.setBalance( BigDecimal.valueOf( transferRequestDto.getAmount() ) );
        account.setPrice( transferRequestDto.getPrice() );

        return account;
    }

    protected User transferRequestDtoToUser(TransferRequestDto transferRequestDto) {
        if ( transferRequestDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( transferRequestDto.getUserId() );

        return user;
    }
}
