package ru.seliverstov.userservice.dto;

public class UserRs {
    private Long id;
    private String fio;
    private String role;

    public UserRs(final Long id, final String fio, final String role) {
        this.id = id;
        this.fio = fio;
        this.role = role;
    }

    public UserRs() {
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
