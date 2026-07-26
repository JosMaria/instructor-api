package org.lievasoft.instructor.repository;

import org.lievasoft.instructor.entity.Locution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLocutionRepository extends JpaRepository<Locution, Long> {
}
