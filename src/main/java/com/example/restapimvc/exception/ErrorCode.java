package com.example.restapimvc.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_INFO_NOT_FOUND(HttpStatus.UNAUTHORIZED, "User information cannot found from token. Please sign in again."),

    USER_ID_NOT_FOUND(HttpStatus.NOT_FOUND,"UserId cannot found."),

    DUPLICATE_RESOURCE(HttpStatus.CONFLICT, "Data already exists."),
        ;
    private final HttpStatus httpStatus;
    private final String message;
}
