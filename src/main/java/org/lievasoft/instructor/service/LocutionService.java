package org.lievasoft.instructor.service;

import org.lievasoft.instructor.dto.locution.LocutionCreateDTO;
import org.lievasoft.instructor.dto.locution.LocutionResponse;

public interface LocutionService {

	LocutionResponse create(LocutionCreateDTO locutionCreateDTO);
}
