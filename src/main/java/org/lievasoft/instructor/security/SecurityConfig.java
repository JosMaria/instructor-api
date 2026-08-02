package org.lievasoft.instructor.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {
		return http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/actuator/**").permitAll()
						.requestMatchers("/api/v1/locutions").hasRole("ADMIN")
						.requestMatchers("/api/v1/accounts/register").permitAll()
						.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.build();
	}
}
