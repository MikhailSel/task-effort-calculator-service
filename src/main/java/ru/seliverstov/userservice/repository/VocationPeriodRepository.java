package ru.seliverstov.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.seliverstov.userservice.model.entity.VocationPeriod;

public interface VocationPeriodRepository extends JpaRepository<VocationPeriod, Long> {
}
