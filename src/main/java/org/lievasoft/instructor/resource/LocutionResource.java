package org.lievasoft.instructor.resource;

import org.lievasoft.instructor.dto.locution.LocutionCreateDTO;
import org.lievasoft.instructor.dto.locution.LocutionResponse;
import org.lievasoft.instructor.service.LocutionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("api/v1/locutions")
public class LocutionResource {

	private final LocutionService locutionService;

	public LocutionResource(LocutionService locutionService) {
		this.locutionService = locutionService;
	}

	@PostMapping
	public ResponseEntity<LocutionResponse> create(@RequestBody LocutionCreateDTO locutionCreateDTO) {
		var locutionResponse = locutionService.create(locutionCreateDTO);
		return ResponseEntity.created(URI.create("/api/v1/locutions")).body(locutionResponse);
	}
}
