package org.lievasoft.instructor.dto.locution;

import java.util.List;

public record LocutionCreateDto(
		String sentence,
		List<String> examples,
		List<String> translations
) {
}
