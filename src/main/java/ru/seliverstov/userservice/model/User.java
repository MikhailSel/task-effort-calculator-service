package ru.seliverstov.userservice.model;

import java.util.Random;

public class User {
    private Long id;
    private String fio;
    private String role;

    public User(final String fio, final String role) {
        this.id = new Random().nextLong(1000);
        this.fio = fio;
        this.role = role;
    }

    public User() {
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

    public String getRole() {
        return role;
    }

    public void setRole(final String role) {
        this.role = role;
    }
}
