package ru.seliverstov.userservice.dto;

public class AddVocationPeriodRs {

    public Long id;

    public Long userId;
    //ожидаем дату в формате dd.mm.yyyy
    private String dateFrom;
    //ожидаем дату в формате dd.mm.yyyy
    private String dateTo;

    public AddVocationPeriodRs() {

    }

    public AddVocationPeriodRs(final Long id, final Long userId, final String dateFrom, final String dateTo) {
        this.id = id;
        this.userId = userId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(final String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(final String dateTo) {
        this.dateTo = dateTo;
    }
}
