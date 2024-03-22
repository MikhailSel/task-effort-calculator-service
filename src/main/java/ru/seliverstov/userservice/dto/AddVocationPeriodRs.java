package ru.seliverstov.userservice.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder

public class AddVocationPeriodRs {

    public Long id;

    public Long userId;
    //ожидаем дату в формате dd.mm.yyyy
    private String dateFrom;
    //ожидаем дату в формате dd.mm.yyyy
    private String dateTo;
}
