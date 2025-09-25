package com.example.mentalhealth.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {

    public static String hashPassword(String password) {
        try {
            // Generate a random salt
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            // Create MessageDigest instance for SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);

            // Add password bytes to digest
            byte[] hashedPassword = md.digest(password.getBytes());

            // Combine salt and hash
            byte[] saltedHash = new byte[salt.length + hashedPassword.length];
            System.arraycopy(salt, 0, saltedHash, 0, salt.length);
            System.arraycopy(hashedPassword, 0, saltedHash, salt.length, hashedPassword.length);

            return Base64.getEncoder().encodeToString(saltedHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    public static boolean checkPassword(String password, String storedHash) {
        try {
            // Decode the stored hash
            byte[] saltedHash = Base64.getDecoder().decode(storedHash);

            // Extract salt (first 16 bytes)
            byte[] salt = new byte[16];
            System.arraycopy(saltedHash, 0, salt, 0, 16);

            // Create MessageDigest instance for SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);

            // Add password bytes to digest
            byte[] hashedPassword = md.digest(password.getBytes());

            // Extract stored hash (remaining bytes)
            byte[] storedPasswordHash = new byte[saltedHash.length - 16];
            System.arraycopy(saltedHash, 16, storedPasswordHash, 0, storedPasswordHash.length);

            // Compare the hashes
            return MessageDigest.isEqual(hashedPassword, storedPasswordHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error checking password", e);
        }
    }
}
