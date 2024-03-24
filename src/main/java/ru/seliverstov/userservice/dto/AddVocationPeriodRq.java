package ru.seliverstov.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddVocationPeriodRq {

    private Long userId;
    //ожидаем дату в формате dd.mm.yyyy
    private String dateFrom;
    //ожидаем дату в формате dd.mm.yyyy
    private String dateTo;

}
