package ru.seliverstov.userservice.model;

import java.time.LocalDate;
import java.util.Random;

public class VocationPeriod {

    private Long id;

    private Long userId;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    public VocationPeriod(final Long userId, final LocalDate dateFrom, final LocalDate dateTo) {
        this.id = new Random().nextLong(1000);
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

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(final LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(final LocalDate dateTo) {
        this.dateTo = dateTo;
    }
}
