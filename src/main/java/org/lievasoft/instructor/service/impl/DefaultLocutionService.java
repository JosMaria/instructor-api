package org.lievasoft.instructor.service.impl;

import org.lievasoft.instructor.dto.locution.LocutionCreateDTO;
import org.lievasoft.instructor.dto.locution.LocutionResponse;
import org.lievasoft.instructor.entity.Locution;
import org.lievasoft.instructor.repository.JpaLocutionRepository;
import org.lievasoft.instructor.service.LocutionService;
import org.springframework.stereotype.Service;

@Service
public class DefaultLocutionService implements LocutionService {

	private final JpaLocutionRepository jpaLocutionRepository;

	public DefaultLocutionService(JpaLocutionRepository jpaLocutionRepository) {
		this.jpaLocutionRepository = jpaLocutionRepository;
	}

	@Override
	public LocutionResponse create(LocutionCreateDTO locutionCreateDTO) {
		var locution = mapToLocution(locutionCreateDTO);
		var persistedLocution = jpaLocutionRepository.save(locution);
		return mapToLocutionResponse(persistedLocution);
	}

	private Locution mapToLocution(LocutionCreateDTO locutionCreateDTO) {
		return new Locution(locutionCreateDTO);
	}

	private LocutionResponse mapToLocutionResponse(Locution locution) {
		return new LocutionResponse(locution.getId(), locution.getSentence());
	}
}
