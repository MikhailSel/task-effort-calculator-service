package ru.seliverstov.userservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    ERR_CODE_001("ERR.CODE.001", "Task with id %s not found", 404),
    ERR_CODE_002("ERR.CODE.002", "User with id %s not found", 404),
    ERR_CODE_003("ERR.CODE.003", "TaskEstimation with idTask %s and idUser %s not found", 404),
    ERR_CODE_004("ERR.CODE.004", "VacationPeriod with id %s not found", 404),
    ERR_CODE_005("ERR.CODE.005", "Один из параметров должен быть заполнен {id, email}", 400),
    ERR_CODE_006("ERR.CODE.006", "Пользователь с email %s не существует", 400);

    private final String code;
    private final String description;
    private final Integer httpCode;

    public String formatDescription(final Object... args) {
        return String.format(description, args);
    }
}
