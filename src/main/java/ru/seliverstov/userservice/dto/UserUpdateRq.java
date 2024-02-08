package ru.seliverstov.userservice.dto;

public class UserUpdateRq {
    private String fio;

    public UserUpdateRq(String fio) {
        this.fio = fio;
    }

    public UserUpdateRq() {
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }
}
