package org.lievasoft.instructor.dto.auth;

import org.lievasoft.instructor.enums.Role;

public record AccountRegisterDto(
        String username,
        String password,
        Role role
) {
}
