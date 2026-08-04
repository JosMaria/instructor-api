package org.lievasoft.instructor.dto.auth;

public record LoginRequest(
		String username,
		String password
) {
}
