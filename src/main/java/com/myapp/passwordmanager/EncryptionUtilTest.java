package com.myapp.passwordmanager;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtilTest {

    @Test
    public void testEncryptDecrypt() {
        String original = "mySecretPassword123"; // This is the string to encrypt and decrypt
        SecretKey secretKey = EncryptionUtil.generateSecretKey();

        // Encrypt the original string
        String encrypted = EncryptionUtil.encrypt(original, secretKey);

        // Decrypt the encrypted string
        String decrypted = EncryptionUtil.decrypt(encrypted, secretKey);

        // Assert that the decrypted string is the same as the original
        assertEquals(original, decrypted, "Decrypted text should match the original text.");
    }

    @Test
    public void testGenerateSecretKey() {
        SecretKey key = EncryptionUtil.generateSecretKey();
        assertNotNull(key, "Generated key should not be null.");
    }

    @Test
    public void testEncryptDecryptWithDifferentKeys() {
        String original = "mySecretPassword123";
        SecretKey key1 = EncryptionUtil.generateSecretKey();
        SecretKey key2 = new SecretKeySpec(new byte[16], "AES"); // Different key for testing

        // Encrypt using the first key
        String encrypted = EncryptionUtil.encrypt(original, key1);

        // Attempt to decrypt using the second key, should fail
        String decrypted = EncryptionUtil.decrypt(encrypted, key2);

        // Assert that the decrypted string does not match the original (should fail)
        assertNotEquals(original, decrypted, "Decrypted text with a different key should not match the original.");
    }
}
