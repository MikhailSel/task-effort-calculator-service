package ru.seliverstov.userservice.dto;

public class UserRs {
    private Long id;
    private String fio;
    private String role;

    public UserRs(Long id, String fio, String role) {
        this.id = id;
        this.fio = fio;
        this.role = role;
    }

    public UserRs() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
