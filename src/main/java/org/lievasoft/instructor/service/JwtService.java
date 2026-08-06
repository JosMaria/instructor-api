package org.lievasoft.instructor.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.lievasoft.instructor.entity.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

	@Value("${jwt.expiration:300000}")
	private Long expiration;

	private final SecretKey key;

	public JwtService(SecretKey key) {
		this.key = key;
	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public boolean isExpiredToken(String token) {
		Date exp = extractClaim(token, Claims::getExpiration);
		return exp.before(new Date());
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser()
				.verifyWith(key)
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}

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
				.signWith(key)
				.compact();
	}
}
