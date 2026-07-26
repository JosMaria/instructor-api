package org.lievasoft.instructor.repository;

import org.lievasoft.instructor.dto.locution.DetailsLocutionResponse;

public interface LocutionDao {

	DetailsLocutionResponse getDetailsLocutionResponseById(Long locutionId);
}
