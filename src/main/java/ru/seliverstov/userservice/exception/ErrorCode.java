package ru.seliverstov.userservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    ERR_CODE_001("ERR.CODE.001", "Task with id %s not found", 404);

    private final String code;
    private final String description;
    private final Integer httpCode;

    public String formatDescription(final Object... args) {
        return String.format(description, args);
    }
}
