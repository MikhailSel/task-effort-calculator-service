package ru.seliverstov.userservice.mapper;

import org.springframework.stereotype.Component;
import ru.seliverstov.userservice.model.dto.AddVacationPeriodRs;
import ru.seliverstov.userservice.model.entity.VacationPeriod;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class VacationPeriodMapper {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public LocalDate toLocalDate(final String stringDate) {
        //final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(stringDate, formatter);
    }

    public AddVacationPeriodRs toAddVacationPeriodRs(final VacationPeriod vacationPeriod) {
        return AddVacationPeriodRs.builder()
            .id(vacationPeriod.getId())
            .userId(vacationPeriod.getUser().getId())
            .dateFrom(vacationPeriod.getDateFrom().format(formatter))
            .dateTo(vacationPeriod.getDateTo().format(formatter))
            .build();
    }
}

