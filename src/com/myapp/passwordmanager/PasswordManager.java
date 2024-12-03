package com.myapp.passwordmanager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PasswordManager {
    private List<PasswordEntry> passwordEntries;
    private static final String FILE_NAME = "passwords.dat";

    public PasswordManager() {
        // Load existing passwords from file
        File file = new File(FILE_NAME);
        if (file.exists()) {
            passwordEntries = FileUtil.loadPasswordsFromFile(FILE_NAME);
        } else {
            passwordEntries = new ArrayList<>();
        }
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
            String decryptedPassword = EncryptionUtil.decrypt(entry.getEncryptedPassword());
            System.out.println("Platform: " + entry.getPlatform() +
                               ", Username: " + entry.getUsername() +
                               ", Password: " + decryptedPassword);
        }
    }

    // Save passwords to file before exit
    public void savePasswords() {
        FileUtil.savePasswordsToFile(passwordEntries, FILE_NAME);
    }
}

