package org.lievasoft.instructor.service;

import org.lievasoft.instructor.dto.AccountRegisterDTO;
import org.lievasoft.instructor.dto.AccountRegisterResponse;
import org.lievasoft.instructor.entity.Account;
import org.lievasoft.instructor.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountService(AccountRepository accountRepository, PasswordEncoder encoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = encoder;
    }

    public AccountRegisterResponse register(AccountRegisterDTO accountRegisterDTO) {
        var accountToPersist = toAccount(accountRegisterDTO);
        var persistedAccount = accountRepository.save(accountToPersist);
        return toAccountRegisterResponse(persistedAccount);
    }

    private Account toAccount(AccountRegisterDTO accountRegisterDTO) {
        return new Account(
                accountRegisterDTO.username(),
                passwordEncoder.encode(accountRegisterDTO.password()),
                accountRegisterDTO.role()
        );
    }

    private AccountRegisterResponse toAccountRegisterResponse(Account account) {
        return new AccountRegisterResponse(
                account.getId(),
                account.getUsername(),
                account.getRole()
        );
    }
}
