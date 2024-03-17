package ru.seliverstov.userservice.dto;

public class UserUpdateRq {

    private Long id;
    private String fio;

    public UserUpdateRq(final Long id, final String fio) {
        this.id = id;
        this.fio = fio;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(final String fio) {
        this.fio = fio;
    }
}
