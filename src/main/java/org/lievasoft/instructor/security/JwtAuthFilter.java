package org.lievasoft.instructor.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.lievasoft.instructor.service.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
public class JwtAuthFilter extends OncePerRequestFilter {

	private final UserDetailsService userDetailsService;
	private final JwtService jwtService;

	public JwtAuthFilter(UserDetailsService userDetailsService, JwtService jwtService) {
		this.userDetailsService = userDetailsService;
		this.jwtService = jwtService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		var prefixBearer = "Bearer ";
		if (authHeader != null && authHeader.startsWith(prefixBearer)) {
			var token = authHeader.substring(prefixBearer.length());
			var username = jwtService.extractUsername(token);
			var authentication = SecurityContextHolder.getContext().getAuthentication();
			if (username != null && authentication == null) {
				var userDetails = userDetailsService.loadUserByUsername(username);
				if (!jwtService.isExpiredToken(token)) {
					var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					var webAuthenticationDetails = new WebAuthenticationDetailsSource().buildDetails(request);
					authToken.setDetails(webAuthenticationDetails);
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}
		}
		filterChain.doFilter(request, response);
	}
}
