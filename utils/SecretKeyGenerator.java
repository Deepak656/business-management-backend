package com.example.lpgmanagement.utils;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Base64;

public class SecretKeyGenerator {
    public static void main(String[] args) {
        // Generate a secure key specifically for HS512
        byte[] keyBytes = Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();
        String secretKey = Base64.getEncoder().encodeToString(keyBytes);
        System.out.println(secretKey);
    }
}