package org.lievasoft.instructor.dto.locution;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record DetailsLocutionResponse(
		@JsonProperty("locution_id")
		Long locutionId,
		String sentence,
		List<String> translations
) {
}
