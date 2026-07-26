package org.lievasoft.instructor.service;

import org.lievasoft.instructor.dto.locution.DetailsLocutionResponse;
import org.lievasoft.instructor.dto.locution.LocutionCreateDto;
import org.lievasoft.instructor.dto.locution.LocutionResponse;

public interface LocutionService {

	LocutionResponse create(LocutionCreateDto locutionCreateDTO);

	DetailsLocutionResponse obtainLocutionResponseById(Long locutionId);
}
