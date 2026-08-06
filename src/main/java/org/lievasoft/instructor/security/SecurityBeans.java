package org.lievasoft.instructor.security;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.lievasoft.instructor.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.SecretKey;

@Configuration
public class SecurityBeans {

	@Value("${jwt.secret}")
	String secretKey;

	@Bean
	public UserDetailsService userDetailsService(AccountRepository accountRepository) {
		return username -> {
			var errorMsg = "Account with username %s does not exist".formatted(username);
			return accountRepository.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException(errorMsg));
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
		return config.getAuthenticationManager();
	}

	@Bean
	public SecretKey secretKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	@Bean
	public JwtDecoder jwtDecoder() {
		var secretKey = secretKey();
		return NimbusJwtDecoder.withSecretKey(secretKey).build();
	}
}
