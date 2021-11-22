package com.innovance.controller;

import com.innovance.entity.Account;
import com.innovance.service.AccountService;
import com.innovance.shared.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/create/{identityNumber}")
    public GenericResponse createAccount(@PathVariable final String identityNumber,
                                         @Valid @RequestBody Account account) {
        accountService.createAccount(identityNumber, account);
        return new GenericResponse("Account created successfully");
    }

    @GetMapping("/get/{identityNumber}")
    public List<Account> getAccount(@PathVariable final String identityNumber) {
        return accountService.getAccounts(identityNumber);
    }

    @GetMapping("/get/{identityNumber}/{accountNumber}")
    public Account getAccount(@PathVariable final String identityNumber,
                              @PathVariable final Long accountNumber) {
        return accountService.getAccount(identityNumber, accountNumber);
    }

    @GetMapping("/getAll")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PutMapping("/update/{identityNumber}/{accountNumber}")
    public GenericResponse updateAccount(@PathVariable final String identityNumber,
                                         @PathVariable final Long accountNumber,
                                         @Valid @RequestBody Account account) {
        accountService.updateAccount(identityNumber, accountNumber, account);
        return new GenericResponse("Account updated successfully");
    }

    @DeleteMapping("/delete/{identityNumber}/{accountNumber}")
    public GenericResponse deleteAccount(@PathVariable final String identityNumber,
                                         @PathVariable final Long accountNumber) {
        accountService.deleteAccount(identityNumber, accountNumber);
        return new GenericResponse("Account deleted successfully");
    }

    @PutMapping("/send/{identityNumber}/{accountNumber}/to/{toAccountNumber}")
    public GenericResponse sendMoney(@PathVariable final String identityNumber,
                                     @PathVariable final Long accountNumber,
                                     @RequestBody final BigDecimal amount,
                                     @PathVariable final Long toAccountNumber) {
        accountService.sendMoney(identityNumber, accountNumber, amount, toAccountNumber);
        return new GenericResponse("Money sent successfully");
    }

    @PutMapping("/sendToOtherUser/{identityNumber}/{accountNumber}/to/{identityNumberUser2}/{accountNumberUser2}")
    public GenericResponse sendMoneyOtherUser(@PathVariable final String identityNumber,
                                              @PathVariable final Long accountNumber,
                                              @RequestBody final BigDecimal amount,
                                              @PathVariable final String identityNumberUser2,
                                              @PathVariable final String accountNumberUser2) {
        return accountService.sendMoneyToTwoUser(identityNumber, accountNumber,amount, identityNumberUser2, accountNumberUser2);
    }

}
