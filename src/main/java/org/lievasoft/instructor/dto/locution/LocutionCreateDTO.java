package org.lievasoft.instructor.dto.locution;

import java.util.List;

public record LocutionCreateDTO(
		String sentence,
		List<String> examples
) {
}
