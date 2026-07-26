package org.lievasoft.instructor.resource;

import org.lievasoft.instructor.dto.locution.DetailsLocutionResponse;
import org.lievasoft.instructor.dto.locution.LocutionCreateDto;
import org.lievasoft.instructor.dto.locution.LocutionResponse;
import org.lievasoft.instructor.service.LocutionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("api/v1/locutions")
public class LocutionResource {

	private final LocutionService locutionService;

	public LocutionResource(LocutionService locutionService) {
		this.locutionService = locutionService;
	}

	@PostMapping
	public ResponseEntity<LocutionResponse> create(@RequestBody LocutionCreateDto locutionCreateDto) {
		var locutionResponse = locutionService.create(locutionCreateDto);
		return ResponseEntity.created(URI.create("/api/v1/locutions")).body(locutionResponse);
	}

	@GetMapping("/{locutionId}")
	public ResponseEntity<DetailsLocutionResponse> fetchById(@PathVariable Long locutionId) {
		var detailsLocutionResponse = locutionService.obtainLocutionResponseById(locutionId);
		return ResponseEntity.ok(detailsLocutionResponse);
	}
}
