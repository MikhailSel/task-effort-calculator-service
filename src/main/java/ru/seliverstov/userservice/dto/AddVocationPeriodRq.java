package ru.seliverstov.userservice.dto;

import java.time.LocalDate;

public class AddVocationPeriodRq {

    public Long userId;
    //ожидаем дату в формате dd.mm.yyyy
    private String dateFrom;
    //ожидаем дату в формате dd.mm.yyyy
    private String dateTo;

    public AddVocationPeriodRq(Long userId, String dateFrom, String dateTo) {
        this.userId = userId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public AddVocationPeriodRq() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
}
