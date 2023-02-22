package com.example.kursova.controllers;

import com.example.kursova.dtos.UserDto;
import com.example.kursova.services.AuthService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class RegisterController extends Controller {

    @FXML
    private Button registerButton;

    @FXML
    private TextField registerFormName;

    @FXML
    private TextField registerFormSurname;

    @FXML
    private TextField registerFormLogin;

    @FXML
    private PasswordField registerFormPassword;

    @FXML
    private PasswordField registerFormRepeatPassword;

    @FXML
    private Button registerGoBackButton;

    @FXML
    private Label registerErrorMessage;

    private final AuthService authService = new AuthService();

    @FXML
    public void initialize() {
        registerButton.setOnAction(event -> register());
        registerGoBackButton.setOnAction(event -> openScene("login", registerGoBackButton));
    }

    private void register() {
        if (!registerFormPassword.getText().equals(registerFormRepeatPassword.getText())) {
            registerErrorMessage.setText("Паролі не співпадають");
            return;
        }
        if (registerFormName.getText().isBlank() ||
            registerFormSurname.getText().isBlank() ||
            registerFormLogin.getText().isBlank() ||
            registerFormPassword.getText().isBlank()
        ) {
            registerErrorMessage.setText("Заповніть усі поля");
            return;
        }
        UserDto user = new UserDto(
                registerFormName.getText(),
                registerFormSurname.getText(),
                registerFormLogin.getText(),
                registerFormPassword.getText()
        );
        boolean isSuccessfullyRegistered = authService.register(user);
        if (!isSuccessfullyRegistered) {
            registerErrorMessage.setText("Користувач із таким логіном уже існує");
            return;
        }
        openScene("login", registerButton);
    }
}
