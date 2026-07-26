package org.lievasoft.instructor.service.impl;

import org.lievasoft.instructor.dto.locution.DetailsLocutionResponse;
import org.lievasoft.instructor.dto.locution.LocutionCreateDto;
import org.lievasoft.instructor.dto.locution.LocutionResponse;
import org.lievasoft.instructor.entity.Locution;
import org.lievasoft.instructor.repository.LocutionDao;
import org.lievasoft.instructor.repository.LocutionRepository;
import org.lievasoft.instructor.service.LocutionService;
import org.springframework.stereotype.Service;

@Service
public class DefaultLocutionService implements LocutionService {

	private final LocutionRepository locutionRepository;
	private final LocutionDao locutionDao;

	public DefaultLocutionService(LocutionRepository locutionRepository, LocutionDao locutionDao) {
		this.locutionRepository = locutionRepository;
		this.locutionDao = locutionDao;
	}

	@Override
	public LocutionResponse create(LocutionCreateDto locutionCreateDto) {
		var locution = mapToLocution(locutionCreateDto);
		var persistedLocution = locutionRepository.save(locution);
		return mapToLocutionResponse(persistedLocution);
	}

	@Override
	public DetailsLocutionResponse obtainLocutionResponseById(Long locutionId) {
		return locutionDao.getDetailsLocutionResponseById(locutionId);
	}

	private Locution mapToLocution(LocutionCreateDto locutionCreateDto) {
		return new Locution(locutionCreateDto);
	}

	private LocutionResponse mapToLocutionResponse(Locution locution) {
		return new LocutionResponse(locution.getId(), locution.getSentence());
	}
}
