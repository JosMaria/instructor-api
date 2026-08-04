package org.lievasoft.instructor.service;

import org.lievasoft.instructor.dto.auth.AccountRegisterDto;
import org.lievasoft.instructor.dto.auth.AuthResponse;
import org.lievasoft.instructor.dto.auth.LoginRequest;
import org.lievasoft.instructor.entity.Account;
import org.lievasoft.instructor.repository.AccountRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

	private final AccountRepository accountRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	public AccountService(AccountRepository accountRepository, PasswordEncoder encoder, JwtService jwtService,
	                      AuthenticationManager authenticationManager) {
		this.accountRepository = accountRepository;
		this.passwordEncoder = encoder;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}

	public AuthResponse register(AccountRegisterDto accountRegisterDto) {
		var accountToPersist = mapToAccount(accountRegisterDto);
		var persistedAccount = accountRepository.save(accountToPersist);
		var generatedToken = jwtService.generateToken(persistedAccount);
		return new AuthResponse(generatedToken);
	}

	public AuthResponse login(LoginRequest loginRequest) {
		var authentication = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());
		var authenticated = authenticationManager.authenticate(authentication);
		var authenticatedAccount = (Account) authenticated.getPrincipal();
		var generatedToken = jwtService.generateToken(authenticatedAccount);
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
