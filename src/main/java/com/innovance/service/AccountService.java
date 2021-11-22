package com.innovance.service;

import com.innovance.entity.Account;
import com.innovance.entity.User;
import com.innovance.repository.AccountRepository;
import com.innovance.repository.UserRepository;
import com.innovance.shared.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public GenericResponse createAccount(String identityNumber, Account account) {
        final User inDB = userRepository.findByIdentityNumber(identityNumber);
        if (inDB != null) {
            account.setUser(inDB);
            account.setCreatedAt(Calendar.getInstance().getTime().toString());
            accountRepository.save(account);
            return new GenericResponse("Account created successfully");
        }
        return new GenericResponse("User not found");
    }

    public List<Account> getAccounts(String identityNumber) {
        final User inDB = userRepository.findByIdentityNumber(identityNumber);
        return accountRepository.findByUser(inDB);
    }

    public Account getAccount(String identityNumber,
                              Long accountNumber) {
        final User inDB = userRepository.findByIdentityNumber(identityNumber);
        return accountRepository.findByUserAndId(inDB, accountNumber);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public void updateAccount(String identityNumber,
                              Long accountNumber,
                              Account account) {
        final User inDB = userRepository.findByIdentityNumber(identityNumber);
        final Account inDB2 = accountRepository.findByUserAndId(inDB, accountNumber);
        inDB2.setAmount(account.getAmount());
        inDB2.setUser(inDB);
        inDB2.setMoneyType(account.getMoneyType());
        inDB2.setUpdatedAt(account.getUpdatedAt());
        inDB2.setCreatedAt(account.getCreatedAt());
        inDB2.setId(accountNumber);
        accountRepository.save(inDB2);
    }

    public void deleteAccount(String identityNumber, Long accountNumber) {
        final User inDB = userRepository.findByIdentityNumber(identityNumber);
        accountRepository.deleteByUserAndId(inDB, accountNumber);
    }

    @Transactional
    public GenericResponse sendMoney(final String identityNumber,
                          final Long accountNumber,
                          final BigDecimal amount,
                          final Long toAccountNumber) {
        final User inDB = userRepository.findByIdentityNumber(identityNumber);
        final Account inDBAccount1 = accountRepository.findByUserAndId(inDB, accountNumber);
        final Account inDBAccount2 = accountRepository.findByUserAndId(inDB, toAccountNumber);
        if (inDBAccount1.getMoneyType().equals(inDBAccount2.getMoneyType())) {
            if (inDBAccount1.getAmount().compareTo(amount) >= 0) {
                inDBAccount1.setAmount(inDBAccount1.getAmount().subtract(amount));
                inDBAccount2.setAmount(inDBAccount2.getAmount().add(amount));
                accountRepository.save(inDBAccount1);
                accountRepository.save(inDBAccount2);
                return new GenericResponse("Money sent successfully");
            } else {
                return new GenericResponse("Insufficient balance");
            }
        } else {
            return new GenericResponse("Cannot send money between different currencies");
        }
    }

    @Transactional
    public GenericResponse sendMoneyToTwoUser(String identityNumber,
                                     Long accountNumber,
                                     BigDecimal amount,
                                     String identityNumberUser2,
                                     String accountNumberUser2) {

        final User inDBUser1 = userRepository.findByIdentityNumber(identityNumber);
        final Account inDBAccount1 = accountRepository.findByUserAndId(inDBUser1, accountNumber);
        final User inDBUser2 = userRepository.findByIdentityNumber(identityNumberUser2);
        final Account inDBAccount2 = accountRepository.findByUserAndId(inDBUser2, Long.parseLong(accountNumberUser2));
        if (inDBAccount1.getMoneyType().equals(inDBAccount2.getMoneyType())) {
            if (inDBAccount1.getAmount().compareTo(amount) >= 0) {
                inDBAccount1.setAmount(inDBAccount1.getAmount().subtract(amount));
                inDBAccount1.setUpdatedAt(Calendar.getInstance().getTime().toString());
                accountRepository.save(inDBAccount1);
                System.out.println("Account 1 updated");
                System.out.println("inDBAccount1.getAmount(): " + inDBAccount1.getAmount());
                inDBAccount2.setAmount(inDBAccount2.getAmount().add(amount));
                System.out.println("inDBAccount1.getAmount(): " + inDBAccount1.getAmount());
                inDBAccount2.setUpdatedAt(Calendar.getInstance().getTime().toString());
                accountRepository.save(inDBAccount2);
                return new GenericResponse("Money sent");
            }else {
                return new GenericResponse("Not enough money");
            }
        }else {
            return new GenericResponse("Money type not match");
        }
    }
}
