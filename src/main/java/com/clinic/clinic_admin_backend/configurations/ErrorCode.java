package com.clinic.clinic_admin_backend.configurations;

public enum ErrorCode {
    UNAUTHORIZED("E001", "Not authorized"),
    INTERNAL_ERROR("E500", "Internal server error"),
    TOKEN_EXPIRED("E002", "Token has expired"),
    INVALID_TOKEN("E003", "Invalid token"),
    USER_NOT_FOUND("E004", "User not found");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
