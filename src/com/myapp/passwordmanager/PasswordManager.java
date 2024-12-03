package com.myapp.passwordmanager;

import java.util.ArrayList;
import java.util.List;

public class PasswordManager {
    // List to store password entries
    private List<PasswordEntry> passwordEntries;

    // Constructor
    public PasswordManager() {
        this.passwordEntries = new ArrayList<>();
    }

    // Add a new password entry (with encrypted password)
    public void addPasswordEntry(String platform, String username, String plainPassword) {
        String encryptedPassword = EncryptionUtil.encrypt(plainPassword);
        PasswordEntry entry = new PasswordEntry(platform, username, encryptedPassword);
        passwordEntries.add(entry);
    }

    // Retrieve all stored password entries
    public List<PasswordEntry> getAllPasswordEntries() {
        return passwordEntries;
    }

    // Find a password entry by platform name
    public PasswordEntry findPasswordEntryByPlatform(String platform) {
        for (PasswordEntry entry : passwordEntries) {
            if (entry.getPlatform().equalsIgnoreCase(platform)) {
                return entry;
            }
        }
        return null; // Return null if no entry is found
    }

    // Delete a password entry by platform name
    public boolean deletePasswordEntryByPlatform(String platform) {
        PasswordEntry entry = findPasswordEntryByPlatform(platform);
        if (entry != null) {
            passwordEntries.remove(entry);
            return true;
        }
        return false;
    }

    // Display all password entries (for testing purposes)
    public void displayAllPasswords() {
        for (PasswordEntry entry : passwordEntries) {
            // Decrypt and display the password
            String decryptedPassword = EncryptionUtil.decrypt(entry.getEncryptedPassword());
            System.out.println("Platform: " + entry.getPlatform() +
                               ", Username: " + entry.getUsername() +
                               ", Password: " + decryptedPassword);
        }
    }
}
