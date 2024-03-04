package ru.seliverstov.userservice.dto;

import java.time.LocalDate;

public class AddVocationPeriodRs {

    public Long id;

    public Long userId;
    //ожидаем дату в формате dd.mm.yyyy
    private String dateFrom;
    //ожидаем дату в формате dd.mm.yyyy
    private String dateTo;

    public AddVocationPeriodRs() {

    }

    public AddVocationPeriodRs(Long id, Long userId, String dateFrom, String dateTo) {
        this.id = id;
        this.userId = userId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
