package org.lievasoft.instructor.dto;

import org.lievasoft.instructor.enums.Role;

public record AccountRegisterResponse(
        Long id,
        String username,
        Role role
) {
}
