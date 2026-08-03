package org.lievasoft.instructor.service;

import org.lievasoft.instructor.dto.auth.AccountRegisterDto;
import org.lievasoft.instructor.dto.auth.AuthResponse;
import org.lievasoft.instructor.entity.Account;
import org.lievasoft.instructor.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

	private final AccountRepository accountRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	public AccountService(AccountRepository accountRepository, PasswordEncoder encoder, JwtService jwtService) {
		this.accountRepository = accountRepository;
		this.passwordEncoder = encoder;
		this.jwtService = jwtService;
	}

	public AuthResponse register(AccountRegisterDto accountRegisterDto) {
		var accountToPersist = mapToAccount(accountRegisterDto);
		var persistedAccount = accountRepository.save(accountToPersist);
		var generatedToken = jwtService.generateToken(persistedAccount);
		return new AuthResponse(generatedToken);
	}

	private Account mapToAccount(AccountRegisterDto accountRegisterDto) {
		var encodedPassword = passwordEncoder.encode(accountRegisterDto.password());
		return new Account(
				accountRegisterDto.username(),
				encodedPassword,
				accountRegisterDto.role()
		);
	}
}
