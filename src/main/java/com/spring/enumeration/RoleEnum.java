package com.spring.enumeration;

public enum RoleEnum {

    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_CUSTOMER("ROLE_CUSTOMER"),
    ROLE_DENTIST("ROLE_DENTIST"),
    ROLE_RECEPTIONIST ("ROLE_RECEPTIONIST");

    private String value;

    private RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
