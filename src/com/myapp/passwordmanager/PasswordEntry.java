package com.myapp.passwordmanager;

public class PasswordEntry {
    private String platform;  // e.g., "Gmail", "Facebook"
    private String username;  // e.g., "user@example.com"
    private String encryptedPassword;  // Encrypted password

    // Constructor
    public PasswordEntry(String platform, String username, String encryptedPassword) {
        this.platform = platform;
        this.username = username;
        this.encryptedPassword = encryptedPassword;
    }

    // Getters and Setters
    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    @Override
    public String toString() {
        return "Platform: " + platform + "\n" +
               "Username: " + username + "\n" +
               "Encrypted Password: " + encryptedPassword;
    }
}
