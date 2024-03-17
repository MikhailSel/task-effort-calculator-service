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

    private final List<VocationPeriod> vocationPeriods = new ArrayList<>();

    /**
     * Adds a new vocation period.
     *
     * @param addVocationPeriodRq The request object containing the details of the vocation period to be added.
     *                            This request object should have the following properties:
     *                            - userId: The ID of the user associated with the vocation period (type: Long).
     *                            - dateFrom: The starting date of the vocation period in the format "dd.MM.yyyy" (type: String).
     *                            - dateTo: The ending date of the vocation period in the format "dd.MM.yyyy" (type: String).
     * @return The response object containing the details of the added vocation period.
     * This response object will have the following properties:
     * - id: The ID of the vocation period (type: Long).
     * - userId: The ID of the user associated with the vocation period (type: Long).
     * - dateFrom: The starting date of the vocation period in the format "dd.MM.yyyy" (type: String).
     * - dateTo: The ending date of the vocation period in the format "dd.MM.yyyy" (type: String).
     */
    public AddVocationPeriodRs postAddVocationPeriod(AddVocationPeriodRq addVocationPeriodRq) {

        final String dateFrom = addVocationPeriodRq.getDateFrom();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        final LocalDate dateFromLocalDate = LocalDate.parse(dateFrom, formatter);
        final LocalDate dateToLocalDate = LocalDate.parse(dateFrom, formatter);

        final VocationPeriod vocationPeriod = new VocationPeriod(addVocationPeriodRq.getUserId(),
            dateFromLocalDate,
            dateToLocalDate);
        vocationPeriods.add(vocationPeriod);

        VocationPeriod vocationPeriod = new VocationPeriod(addVocationPeriodRq.getUserId(), dateFromLocalDate, dateToLocalDate);
        vocationPeriods.add(vocationPeriod);

        AddVocationPeriodRs addVocationPeriodRs = new AddVocationPeriodRs(vocationPeriod.getId(),
        final AddVocationPeriodRs addVocationPeriodRs = new AddVocationPeriodRs(vocationPeriod.getId(),
            vocationPeriod.getUserId(),
            vocationPeriod.getDateFrom().toString(),
            vocationPeriod.getDateTo().toString());
        return addVocationPeriodRs;
    }

    public AddVocationPeriodRs getVocationPeriod(Long id) {
        for (VocationPeriod vocationPeriod : vocationPeriods) {
            if (vocationPeriod.getId().equals(id)) {
                final AddVocationPeriodRs addVocationPeriodRs = new AddVocationPeriodRs(vocationPeriod.getId(),
                    vocationPeriod.getUserId(),
                    vocationPeriod.getDateFrom().toString(),
                    vocationPeriod.getDateTo().toString());
                return addVocationPeriodRs;
            }
        }
        return null;
    }
}
