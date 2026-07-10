package org.lievasoft.instructor.dto;

import org.lievasoft.instructor.Role;

public record AccountRegisterDTO(
        String username,
        String password,
        Role role
) {
}
