package com.spring.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptUtil {

    public static String getHash(String password) {

        if (password == null) {
            return null;
        }

        if (BcryptUtil.isEncrypted(password)) {
            return password;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.encode(password);

    }

    public static boolean isEncrypted(String password) {
        return password.startsWith("$2a$");
    }
}