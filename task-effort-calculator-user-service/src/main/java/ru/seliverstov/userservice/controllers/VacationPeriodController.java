package ru.seliverstov.userservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.seliverstov.userservice.model.dto.AddVacationPeriodRq;
import ru.seliverstov.userservice.model.dto.AddVacationPeriodRs;
import ru.seliverstov.userservice.model.dto.PutVacationPeriodRq;
import ru.seliverstov.userservice.services.VacationPeriodService;

@RestController
@RequiredArgsConstructor
public class VacationPeriodController {

    private final VacationPeriodService vacationPeriodService;

    //@PostMapping("api/v1/vacation-periods")
    @PostMapping(value = "api/v1/vacation-periods"/*, produces = "application/xml"*/, consumes = "application/json")
    public AddVacationPeriodRs postAddVacationPeriodRs(@RequestBody final AddVacationPeriodRq addVacationPeriodRq) {
        return vacationPeriodService.postAddVocationPeriod(addVacationPeriodRq);
    }

    //http://localhost:8080/api/v1/vocation-periods/12
    @GetMapping("api/v1/vacation-periods/{id}")
    public AddVacationPeriodRs getVacationPeriod(@PathVariable final Long id) {
        return vacationPeriodService.getVocationPeriod(id);
    }

    @DeleteMapping("api/v1/vacation-periods/{id}")
    public void deleteVacationPeriod(@PathVariable final Long id) {
        vacationPeriodService.deleteVacationPeriod(id);
    }

    @PutMapping("api/v1/vacation-periods")
    public AddVacationPeriodRs putVacationPeriod(@RequestBody final PutVacationPeriodRq request) {
        return vacationPeriodService.putVacationPeriod(request);
    }
}



