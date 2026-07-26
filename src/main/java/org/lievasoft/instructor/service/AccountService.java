package org.lievasoft.instructor.service;

import org.lievasoft.instructor.dto.AccountRegisterDto;
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

	public AccountRegisterResponse register(AccountRegisterDto accountRegisterDto) {
		var accountToPersist = mapToAccount(accountRegisterDto);
		var persistedAccount = accountRepository.save(accountToPersist);
		return mapToAccountRegisterResponse(persistedAccount);
	}

	private Account mapToAccount(AccountRegisterDto accountRegisterDto) {
		var encodedPassword = passwordEncoder.encode(accountRegisterDto.password());
		return new Account(
				accountRegisterDto.username(),
				encodedPassword,
				accountRegisterDto.role()
		);
	}

	private AccountRegisterResponse mapToAccountRegisterResponse(Account account) {
		return new AccountRegisterResponse(
				account.getId(),
				account.getUsername(),
				account.getRole()
		);
	}
}
