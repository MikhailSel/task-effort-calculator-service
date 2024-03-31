package ru.seliverstov.userservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.seliverstov.userservice.dto.AddVocationPeriodRq;
import ru.seliverstov.userservice.dto.AddVocationPeriodRs;
import ru.seliverstov.userservice.services.VocationPeriodService;

@RestController
public class VocationPeriodController {

    private final VocationPeriodService vocationPeriodService;

    public VocationPeriodController(final VocationPeriodService vocationPeriodService) {
        this.vocationPeriodService = vocationPeriodService;
    }

    @PostMapping(value = "api/v1/vocation-periods", produces = "application/xml", consumes = "application/json")
    public AddVocationPeriodRs postAddVocationPeriodRs(@RequestBody final AddVocationPeriodRq addVocationPeriodRq) {
        final AddVocationPeriodRs addVocationPeriodRs = vocationPeriodService.postAddVocationPeriod(addVocationPeriodRq);
        return addVocationPeriodRs;
    }

    //http://localhost:8080/api/v1/vocation-periods/12
    @GetMapping("api/v1/vocation-periods/{id}")
    public AddVocationPeriodRs getVocationPeriod(@PathVariable final Long id) {
        final AddVocationPeriodRs addVocationPeriodRs = vocationPeriodService.getVocationPeriod(id);
        return addVocationPeriodRs;
    }
}



