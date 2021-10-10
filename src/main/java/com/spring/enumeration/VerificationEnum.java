package com.spring.enumeration;

public enum VerificationEnum {

    VERIFY_ACCOUNT("VERIFY_ACCOUNT"),
    VERIFY_CHANGE_PASSWORD("VERIFY_CHANGE_PASSWORD");

    private String value;

    private VerificationEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
