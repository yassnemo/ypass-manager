package com.myapp.passwordmanager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PasswordManager passwordManager = new PasswordManager();
        Scanner scanner = new Scanner(System.in);

        // Display menu options
        while (true) {
            System.out.println("Password Manager");
            System.out.println("1. Add Password");
            System.out.println("2. View All Passwords");
            System.out.println("3. Find Password");
            System.out.println("4. Delete Password");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Add a new password
                    System.out.print("Enter platform: ");
                    String platform = scanner.nextLine();
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    passwordManager.addPasswordEntry(platform, username, password);
                    break;
                case 2:
                    // Display all passwords
                    passwordManager.displayAllPasswords();
                    break;
                case 3:
                    // Find a password
                    System.out.print("Enter platform: ");
                    String findPlatform = scanner.nextLine();
                    PasswordEntry entry = passwordManager.findPasswordEntryByPlatform(findPlatform);
                    if (entry != null) {
                        System.out.println("Username: " + entry.getUsername() +
                                           ", Password: " + EncryptionUtil.decrypt(entry.getEncryptedPassword()));
                    } else {
                        System.out.println("Password entry not found.");
                    }
                    break;
                case 4:
                    // Delete a password
                    System.out.print("Enter platform to delete: ");
                    String deletePlatform = scanner.nextLine();
                    if (passwordManager.deletePasswordEntryByPlatform(deletePlatform)) {
                        System.out.println("Password entry deleted.");
                    } else {
                        System.out.println("Password entry not found.");
                    }
                    break;
                case 5:
                    // Exit the program and save passwords to file
                    passwordManager.savePasswords();
                    System.out.println("Passwords saved. Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
