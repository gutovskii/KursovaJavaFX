package com.example.kursova.controllers;

import com.example.kursova.services.AuthService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController extends Controller {
    @FXML
    private Button loginButton;

    @FXML
    private TextField loginFormLogin;

    @FXML
    private PasswordField loginFormPassword;

    @FXML
    private Button loginGoToRegisterButton;

    @FXML
    private Label loginErrorMessage;

    private final AuthService authService = new AuthService();

    @FXML
    public void initialize() {
        loginButton.setOnAction(event -> login());
        loginGoToRegisterButton.setOnAction(event -> openScene("register", loginGoToRegisterButton));
    }

    private void login() {
        if (loginFormLogin.getText().isBlank() || loginFormPassword.getText().isBlank()) {
            loginErrorMessage.setText("Заповніть усі поля");
            return;
        }
        boolean isSuccessfullyLogged = authService.login(loginFormLogin.getText(), loginFormPassword.getText());
        if (!isSuccessfullyLogged) {
            loginErrorMessage.setText("Неправильний логін або пароль");
            return;
        }
        openScene("main", loginButton);
    }
}
