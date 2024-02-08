package ru.seliverstov.userservice.dto;

public class UserRegistrationRq {
    private String fullName;
    private String role;

    public UserRegistrationRq(String fullName, String role) {
        this.fullName = fullName;
        this.role = role;
    }

    public UserRegistrationRq() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
