package com.myapp.passwordmanager;

import java.io.*;
import java.util.List;

public class FileUtil {

    // Save password entries to a file
    public static void savePasswordsToFile(List<PasswordEntry> passwordEntries, String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(passwordEntries);
            System.out.println("Passwords saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving passwords: " + e.getMessage());
        }
    }

    // Load password entries from a file
    @SuppressWarnings("unchecked")
    public static List<PasswordEntry> loadPasswordsFromFile(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<PasswordEntry>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading passwords: " + e.getMessage());
            return null;
        }
    }
}
