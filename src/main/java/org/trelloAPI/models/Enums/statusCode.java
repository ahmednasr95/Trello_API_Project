package org.trelloAPI.models.Enums;

public enum statusCode {
    OK("200"),
    BAD_REQUEST("400"),
    UNAUTHORIZED("401"),
    FORBIDDEN("403"),
    NOT_FOUND("404");
    private final String code;
    statusCode(String code) {
        this.code = code;
    }
    @Override
    public String toString(){
        return code;
    }
}
