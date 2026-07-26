package org.lievasoft.instructor.dto;

import org.lievasoft.instructor.Role;

public record AccountRegisterDto(
        String username,
        String password,
        Role role
) {
}
