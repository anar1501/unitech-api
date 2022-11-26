package az.unibank.unitech.service.impl;

import az.unibank.unitech.config.ApplicationConfig;
import az.unibank.unitech.config.URLConfiguration;
import az.unibank.unitech.data.dao.AccountDaoImpl;
import az.unibank.unitech.data.dto.request.AccountRequestDto;
import az.unibank.unitech.data.dto.request.CreateAccountRequestDto;
import az.unibank.unitech.data.dto.request.TransferRequestDto;
import az.unibank.unitech.data.dto.response.AccountResponseDto;
import az.unibank.unitech.data.dto.response.SavedResponseDto;
import az.unibank.unitech.data.entity.Account;
import az.unibank.unitech.data.entity.User;
import az.unibank.unitech.data.repository.AccountRepository;
import az.unibank.unitech.data.repository.UserRepository;
import az.unibank.unitech.enums.UserStatusEnum;
import az.unibank.unitech.exception.DeactiveAccountException;
import az.unibank.unitech.exception.NoEnoughCashStrangeException;
import az.unibank.unitech.exception.NonAccountExistException;
import az.unibank.unitech.exception.SameAccountException;
import az.unibank.unitech.mapper.AccountMapStruct;
import az.unibank.unitech.mapper.UserMapStruct;
import az.unibank.unitech.service.AccountService;
import az.unibank.unitech.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    @Autowired(required = false)
    private AccountRepository accountRepository;
    @Autowired(required = false)
    private AccountMapStruct accountMapStruct;
    @Autowired(required = false)
    private UserRepository userRepository;
    @Autowired(required = false)
    private UserService userService;
    @Autowired(required = false)
    private UserMapStruct userMapStruct;
    @Autowired(required = false)
    private AccountDaoImpl accountDao;
    @Autowired(required = false)
    private ApplicationConfig applicationConfig;
    @Autowired(required = false)
    private URLConfiguration urlConfiguration;

    @Override
    public List<AccountResponseDto> findAllById(AccountRequestDto accountRequestDto) {
        List<AccountResponseDto> returnValue = new ArrayList<>();
        List<Account> accountsById = accountRepository.findAccountsByUserId(accountRequestDto.getUserId());
        for (Account account : accountsById) {
            if (account.getUser().getStatus().getId().equals(UserStatusEnum.CONFIRMED.getStatusId())) {
                returnValue.add(accountMapStruct.map(account));
            }
        }
        return returnValue;
    }

    @Override
    public SavedResponseDto create(CreateAccountRequestDto createAccountRequestDto) {
        SavedResponseDto returnValue = new SavedResponseDto();
        Account account = accountMapStruct.map(createAccountRequestDto);
        if (userRepository.findById(createAccountRequestDto.getUserId()).isPresent()) {
            User user = userRepository.findById(createAccountRequestDto.getUserId()).get();
            account.setUser(user);
            accountRepository.save(account);
        }
        returnValue.setId(account.getId());
        return returnValue;
    }

    @Override
    public void makeTransferToAnotherAccount(TransferRequestDto transferRequestDto) {
        Optional<User> optional = userRepository.findByEmail(transferRequestDto.getEmail());
        if (optional.isPresent()) {
            User user = optional.get();
            Optional<User> userOptional = userService.login(user.getFin(),user.getPassword());
            if (userOptional.isPresent()) {
                if (userOptional.get().getEmail().equals(transferRequestDto.getEmail())) {
                    throw new SameAccountException();
                }
            }
        }
        if (transferRequestDto.getAmount() == 0.0) {
            throw new NoEnoughCashStrangeException();
        } else if (accountRepository.findTopByUserId(transferRequestDto.getUserId()).get().getUser().getStatus().getId().equals(UserStatusEnum.UNCONFIRMED.getStatusId())) {
            throw new DeactiveAccountException();
        } else if (!accountRepository.findTopByUserId(transferRequestDto.getUserId()).isPresent()) {
            throw new NonAccountExistException();
        }
        Account map = accountMapStruct.map(transferRequestDto);
        map.setId(transferRequestDto.getAccountId());
        accountDao.update(map.getId(), map);
    }

    @SneakyThrows
    @Override
    public String getCurrency(String base) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(urlConfiguration.getCurrencyRatesUrl()).newBuilder();
        urlBuilder.addQueryParameter("base", base);
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).addHeader("apikey", applicationConfig.getApiKey()).build();
        Call call = client.newCall(request);
        Response execute = call.execute();
        return execute.body().string();
    }

}
