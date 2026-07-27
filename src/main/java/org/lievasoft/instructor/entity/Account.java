package org.lievasoft.instructor.entity;

import jakarta.persistence.*;
import org.jspecify.annotations.Nullable;
import org.lievasoft.instructor.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, length = 50)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private Role role;

	public Account() {
	}

	public Account(String username, String password, Role role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public Long getId() {
		return this.id;
	}

	public Role getRole() {
		return this.role;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		var simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + this.role);
		return List.of(simpleGrantedAuthority);
	}

	@Override
	public @Nullable String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return UserDetails.super.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return UserDetails.super.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return UserDetails.super.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return UserDetails.super.isEnabled();
	}
}
