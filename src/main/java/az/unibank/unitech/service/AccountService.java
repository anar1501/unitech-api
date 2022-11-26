package az.unibank.unitech.service;

import az.unibank.unitech.data.dto.request.AccountRequestDto;
import az.unibank.unitech.data.dto.request.CreateAccountRequestDto;
import az.unibank.unitech.data.dto.request.TransferRequestDto;
import az.unibank.unitech.data.dto.response.AccountResponseDto;
import az.unibank.unitech.data.dto.response.SavedResponseDto;

import java.util.List;

public interface AccountService {
    List<AccountResponseDto>findAllById(AccountRequestDto accountRequestDto);

    SavedResponseDto create(CreateAccountRequestDto createAccountRequestDto);

    void makeTransferToAnotherAccount(TransferRequestDto transferRequestDto);

    String getCurrency(String base);
}
