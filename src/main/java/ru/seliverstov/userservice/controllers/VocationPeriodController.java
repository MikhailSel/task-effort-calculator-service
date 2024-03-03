package ru.seliverstov.userservice.controllers;

import org.springframework.web.bind.annotation.*;
import ru.seliverstov.userservice.dto.AddVocationPeriodRq;
import ru.seliverstov.userservice.dto.AddVocationPeriodRs;
import ru.seliverstov.userservice.services.VocationPeriodService;

@RestController
public class VocationPeriodController {

    private final VocationPeriodService vocationPeriodService;

    public VocationPeriodController(VocationPeriodService vocationPeriodService) {
        this.vocationPeriodService = vocationPeriodService;
    }

    @PostMapping("api/v1/vocation-periods")
    public AddVocationPeriodRs postAddVocationPeriodRs(@RequestBody AddVocationPeriodRq addVocationPeriodRq) {
        AddVocationPeriodRs addVocationPeriodRs = vocationPeriodService.postAddVocationPeriod(addVocationPeriodRq);
        return addVocationPeriodRs;
    }

    @GetMapping("api/v1/vocation-periods")
    public AddVocationPeriodRs getVocationPeriod(@RequestParam Long id) {
        AddVocationPeriodRs addVocationPeriodRs = vocationPeriodService.getVocationPeriod(id);
        return addVocationPeriodRs;

    }
}



