package org.lievasoft.instructor.dto;

import org.lievasoft.instructor.Role;

public record AccountRegisterResponse(
        Long id,
        String username,
        Role role
) {
}
