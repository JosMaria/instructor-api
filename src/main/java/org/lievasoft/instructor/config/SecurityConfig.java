package org.lievasoft.instructor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        return http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/api/v1/locutions").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails jose = User.builder()
                .username("jose")
                .password("116a48c4db377881517e6832205f9393cc1320da2e04f156c50d0637a98b1a94d5f1974539f29bb8aec01a7487f45dee")
                .roles("ADMIN")
                .build();

        UserDetails maria = User.builder()
                .username("maria")
                .password("700d6218be4803f3a1761f3307831e9699f997e0e354b16964b880732c2648d747e38e22d6327e8607ee502da10b1145")
                .roles("ASSISTANT")
                .build();

        UserDetails pepito = User.builder()
                .username("pepito")
                .password("08109cdcf3abd5f2c991896464de7d40de75ca29fad375998143b70a5c8793b4222add1c07aff0b631a3bc980bd88a31")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(jose, maria, pepito);
    }
}
