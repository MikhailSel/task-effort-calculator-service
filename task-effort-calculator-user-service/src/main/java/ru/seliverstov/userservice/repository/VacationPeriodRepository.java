package ru.seliverstov.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.seliverstov.userservice.model.entity.VacationPeriod;

public interface VacationPeriodRepository extends JpaRepository<VacationPeriod, Long> {
}
