package ru.seliverstov.userservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AddVacationPeriodRs {

    private Long id;

    private Long userId;
    //ожидаем дату в формате dd.mm.yyyy
    private String dateFrom;
    //ожидаем дату в формате dd.mm.yyyy
    private String dateTo;
}
