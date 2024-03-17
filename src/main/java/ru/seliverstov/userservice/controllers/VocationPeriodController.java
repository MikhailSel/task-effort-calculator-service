package ru.seliverstov.userservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("api/v1/vocation-periods")
    public AddVocationPeriodRs postAddVocationPeriodRs(@RequestBody final AddVocationPeriodRq addVocationPeriodRq) {
        final AddVocationPeriodRs addVocationPeriodRs = vocationPeriodService.postAddVocationPeriod(addVocationPeriodRq);
        return addVocationPeriodRs;
    }

    @GetMapping("api/v1/vocation-periods")
    public AddVocationPeriodRs getVocationPeriod(@RequestParam final Long id) {
        final AddVocationPeriodRs addVocationPeriodRs = vocationPeriodService.getVocationPeriod(id);
        return addVocationPeriodRs;
    }
}



