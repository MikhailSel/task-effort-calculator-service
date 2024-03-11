package ru.seliverstov.userservice.services;

import org.springframework.stereotype.Service;
import ru.seliverstov.userservice.dto.AddVocationPeriodRq;
import ru.seliverstov.userservice.dto.AddVocationPeriodRs;
import ru.seliverstov.userservice.model.VocationPeriod;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
public class VocationPeriodService {


    private final List<VocationPeriod> VocationPeriods = new ArrayList<>();

    public AddVocationPeriodRs postAddVocationPeriod(AddVocationPeriodRq addVocationPeriodRq) {

        String dateFrom = addVocationPeriodRq.getDateFrom();
        String dateTo = addVocationPeriodRq.getDateTo();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateFromLocalDate = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToLocalDate = LocalDate.parse(dateFrom, formatter);

        VocationPeriod vocationPeriod = new VocationPeriod(addVocationPeriodRq.getUserId(), dateFromLocalDate, dateToLocalDate);
        VocationPeriods.add(vocationPeriod);

        AddVocationPeriodRs addVocationPeriodRs = new AddVocationPeriodRs(vocationPeriod.getId(),
            vocationPeriod.getUserId(),
            vocationPeriod.getDateFrom().toString(),
            vocationPeriod.getDateTo().toString());
        return addVocationPeriodRs;
    }

    public AddVocationPeriodRs getVocationPeriod(Long id) {
        for (VocationPeriod vocationPeriod : VocationPeriods) {
            if (vocationPeriod.getId().equals(id)) {
                AddVocationPeriodRs addVocationPeriodRs = new AddVocationPeriodRs(
                    vocationPeriod.getId(),
                    vocationPeriod.getUserId(),
                    vocationPeriod.getDateFrom().toString(),
                    vocationPeriod.getDateTo().toString());
                return addVocationPeriodRs;
            }
        }
        return null;
    }
}
