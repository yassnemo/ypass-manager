package com.myapp.passwordmanager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class PasswordManagerController {

    private PasswordManager passwordManager;

    @FXML
    private TableView<PasswordEntry> passwordTable;

    @FXML
    private TableColumn<PasswordEntry, String> platformColumn;

    @FXML
    private TableColumn<PasswordEntry, String> usernameColumn;

    @FXML
    private TableColumn<PasswordEntry, String> passwordColumn;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button viewButton;

    private ObservableList<PasswordEntry> passwordEntries;

    @FXML
    public void initialize() {
        passwordManager = new PasswordManager();

        // Initialize columns
        platformColumn.setCellValueFactory(new PropertyValueFactory<>("platform"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("encryptedPassword"));

        // Load data
        passwordEntries = FXCollections.observableArrayList(passwordManager.getAllPasswordEntries());
        passwordTable.setItems(passwordEntries);
    }

    @FXML
    private void onAddPassword(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Password");
        dialog.setHeaderText("Enter new password details");

        // Platform
        dialog.setContentText("Platform:");
        String platform = dialog.showAndWait().orElse(null);
        if (platform == null || platform.isBlank()) return;

        // Username
        dialog.setContentText("Username:");
        String username = dialog.showAndWait().orElse(null);
        if (username == null || username.isBlank()) return;

        // Password
        dialog.setContentText("Password:");
        String plainPassword = dialog.showAndWait().orElse(null);
        if (plainPassword == null || plainPassword.isBlank()) return;

        // Add entry to manager and table
        passwordManager.addPasswordEntry(platform, username, plainPassword);
        passwordEntries.setAll(passwordManager.getAllPasswordEntries());
    }

    @FXML
    private void onDeletePassword(ActionEvent event) {
        PasswordEntry selectedEntry = passwordTable.getSelectionModel().getSelectedItem();
        if (selectedEntry == null) {
            showAlert("No Selection", "Please select a password entry to delete.");
            return;
        }

        if (passwordManager.deletePasswordEntryByPlatform(selectedEntry.getPlatform())) {
            passwordEntries.setAll(passwordManager.getAllPasswordEntries());
        } else {
            showAlert("Error", "Could not delete the selected password.");
        }
    }

    @FXML
    private void onViewPassword(ActionEvent event) {
        PasswordEntry selectedEntry = passwordTable.getSelectionModel().getSelectedItem();
        if (selectedEntry == null) {
            showAlert("No Selection", "Please select a password entry to view.");
            return;
        }

        String decryptedPassword = EncryptionUtil.decrypt(selectedEntry.getEncryptedPassword());
        showAlert("Password Details",
                "Platform: " + selectedEntry.getPlatform() +
                "\nUsername: " + selectedEntry.getUsername() +
                "\nPassword: " + decryptedPassword);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
