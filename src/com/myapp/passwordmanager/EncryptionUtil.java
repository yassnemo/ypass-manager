package com.myapp.passwordmanager;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptionUtil {

    private static final String ALGORITHM = "AES";
    private static final String DEFAULT_KEY = "1234567890123456"; // 16-byte key for AES-128

    // Encrypt the password
    public static String encrypt(String password) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(DEFAULT_KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedPassword = cipher.doFinal(password.getBytes());
            return Base64.getEncoder().encodeToString(encryptedPassword);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Decrypt the password
    public static String decrypt(String encryptedPassword) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(DEFAULT_KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedPassword = Base64.getDecoder().decode(encryptedPassword);
            byte[] decryptedPassword = cipher.doFinal(decodedPassword);
            return new String(decryptedPassword);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
