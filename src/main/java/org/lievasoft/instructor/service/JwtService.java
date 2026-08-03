package org.lievasoft.instructor.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.lievasoft.instructor.entity.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

	@Value("${jwt.secret}")
	private String secretKey;

	@Value("${jwt.expiration:300000}")
	private Long expiration;

	public String generateToken(Account account) {
		Map<String, Object> claims = Map.of("role", account.getRole());
		return generateToken(claims, account.getUsername());
	}

	private String generateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder()
				.claims(claims)
				.subject(subject)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(getSignInKey())
				.compact();
	}

	private SecretKey getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
