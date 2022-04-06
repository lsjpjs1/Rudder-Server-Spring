package com.example.restapimvc.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    PASSWORD_WRONG(HttpStatus.UNAUTHORIZED, "Password is wrong"),

    NOT_CLUB_MEMBER(HttpStatus.FORBIDDEN, "This post can read just club member"),
    NOT_SCHOOL_MEMBER(HttpStatus.FORBIDDEN, "This post can read just school member"),
    NO_PERMISSION(HttpStatus.FORBIDDEN, "You don't have permission"),

    USER_INFO_NOT_FOUND(HttpStatus.NOT_FOUND, "User information cannot found from token. Please sign in again."),
    USER_ID_NOT_FOUND(HttpStatus.NOT_FOUND,"UserId cannot found."),
    USER_EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND,"UserEmail cannot found."),
    USER_PROFILE_NOT_FOUND(HttpStatus.NOT_FOUND,"UserProfile cannot found."),
    PROFILE_IMAGE_ID_NOT_FOUND(HttpStatus.NOT_FOUND,"ProfileImageId cannot found."),
    OS_TYPE_NOT_FOUND(HttpStatus.NOT_FOUND,"OS type cannot found."),
    VERIFICATION_CODE_NOT_FOUND(HttpStatus.NOT_FOUND,"VerificationCode cannot found."),
    SCHOOL_ID_NOT_FOUND(HttpStatus.NOT_FOUND,"SchoolId cannot found."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND,"Post cannot found."),
    POST_DELETED(HttpStatus.NOT_FOUND,"Post was deleted"),
    POST_WRITER_BLOCKED(HttpStatus.NOT_FOUND,"Writer of post was blocked"),
    VERIFICATION_CODE_WRONG(HttpStatus.NOT_FOUND,"VerificationCode is wrong"),

    BAD_REQUEST_CONTENT(HttpStatus.NOT_ACCEPTABLE,"Please check params or body"),
    WRONG_EMAIL_FORM(HttpStatus.NOT_ACCEPTABLE,"Email form is wrong"),
    ALREADY_PROCESSED(HttpStatus.NOT_ACCEPTABLE,"This work has already been processed"),


    DUPLICATE_RESOURCE(HttpStatus.CONFLICT, "Data already exists."),

    SEND_EMAIL_FAIL(HttpStatus.INTERNAL_SERVER_ERROR,"Fail to send email")
        ;
    private final HttpStatus httpStatus;
    private final String message;
}
