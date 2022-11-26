package az.unibank.unitech.controller;

import az.unibank.unitech.data.dto.request.AccountRequestDto;
import az.unibank.unitech.data.dto.request.CreateAccountRequestDto;
import az.unibank.unitech.data.dto.request.TransferRequestDto;
import az.unibank.unitech.data.dto.response.AccountResponseDto;
import az.unibank.unitech.data.dto.response.SavedResponseDto;
import az.unibank.unitech.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    @Autowired(required = false)
    private AccountService accountService;

    @PostMapping(value = "/getAccounts")
    public ResponseEntity<List<AccountResponseDto>> getAll(@RequestBody AccountRequestDto accountRequestDto) {
        return ResponseEntity.ok(accountService.findAllById(accountRequestDto));
    }

    @PostMapping(value = "/create-account")
    @ResponseStatus(HttpStatus.CREATED)
    public SavedResponseDto create(@RequestBody CreateAccountRequestDto createAccountRequestDto) {
        return accountService.create(createAccountRequestDto);
    }

    @PostMapping(value = "/make-transfer")
    public String makeTransferToAnotherAccount(@RequestBody TransferRequestDto transferRequestDto){
        accountService.makeTransferToAnotherAccount(transferRequestDto);
        return "Transfer successfully done";
    }

    @GetMapping(value = "/getCurrency")
    public ResponseEntity<String>getCurrency(@RequestParam(value = "base")String base){
        return ResponseEntity.ok(accountService.getCurrency(base));
    }
}