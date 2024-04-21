package ru.seliverstov.userservice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.seliverstov.userservice.dto.AddVocationPeriodRq;
import ru.seliverstov.userservice.dto.AddVocationPeriodRs;
import ru.seliverstov.userservice.model.VocationPeriod;
import ru.seliverstov.userservice.repository.VocationPeriodRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VocationPeriodService {

    private final VocationPeriodRepository vocationPeriodRepository;

    public AddVocationPeriodRs postAddVocationPeriod(final AddVocationPeriodRq addVocationPeriodRq) {
        final String dateFrom = addVocationPeriodRq.getDateFrom();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        final LocalDate dateFromLocalDate = LocalDate.parse(dateFrom, formatter);
        final LocalDate dateToLocalDate = LocalDate.parse(dateFrom, formatter);

        final VocationPeriod vocationPeriod = VocationPeriod.builder()
            .userId(addVocationPeriodRq.getUserId())
            .dateFrom(dateFromLocalDate)
            .dateTo(dateToLocalDate)
            .build();

        vocationPeriodRepository.save(vocationPeriod);

        return AddVocationPeriodRs.builder()
            .id(vocationPeriod.getId())
            .userId(vocationPeriod.getUserId())
            .dateFrom(vocationPeriod.getDateFrom().toString())
            .dateTo(vocationPeriod.getDateTo().toString())
            .build();
    }

    public AddVocationPeriodRs getVocationPeriod(final Long id) {
        final Optional<VocationPeriod> vocationPeriodOptional = vocationPeriodRepository.findById(id);

        if (vocationPeriodOptional.isPresent()) {
            final VocationPeriod vocationPeriod = vocationPeriodOptional.get();

            return AddVocationPeriodRs.builder()
                .id(vocationPeriod.getId())
                .userId(vocationPeriod.getUserId())
                .dateFrom(vocationPeriod.getDateFrom().toString())
                .dateTo(vocationPeriod.getDateTo().toString())
                .build();
        }

        throw new IllegalStateException("Cant find vocation period with id: " + id);

        //        for (VocationPeriod vocationPeriod : vocationPeriods) {
        //            if (vocationPeriod.getId().equals(id)) {
        //                return AddVocationPeriodRs.builder()
        //                    .userId(vocationPeriod.getUserId())
        //                    .dateFrom(vocationPeriod.getDateFrom().toString())
        //                    .dateTo(vocationPeriod.getDateTo().toString())
        //                    .build();
        //            }
        //        }
        //        return null;
    }
}
