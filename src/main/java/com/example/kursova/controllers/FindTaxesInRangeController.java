package com.example.kursova.controllers;

import com.example.kursova.services.TaxService;
import com.example.kursova.store.Store;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FindTaxesInRangeController extends Controller {

    @FXML
    private TextField findTaxesFromField;

    @FXML
    private TextField findTaxesToField;

    @FXML
    private Button findTaxesButton;

    @FXML
    private Button findTaxesGoBackButton;

    @FXML
    private Label findTaxesErrorMessage;

    private final TaxService taxService = new TaxService();

    @FXML
    public void initialize() {
        findTaxesButton.setOnAction(event -> findTaxesInRange());
        findTaxesGoBackButton.setOnAction(event -> openScene("main", findTaxesGoBackButton));
    }

    private void findTaxesInRange() {
        if (findTaxesFromField.getText().isBlank() || findTaxesFromField.getText().isBlank()) {
            findTaxesErrorMessage.setText("Заповніть усі поля");
            return;
        }
        double x1 = Double.parseDouble(findTaxesFromField.getText());
        double x2 = Double.parseDouble(findTaxesToField.getText());
        if (x1 <= 0 || x2 <= 0) {
            findTaxesErrorMessage.setText("Значення діапазону мають бути більше нуля");
            return;
        }
        if (x1 >= x2) {
            findTaxesErrorMessage.setText("Перше значення має бути менше за друге");
            return;
        }
        Store.isSearching = true;
        Store.taxes = taxService.findTaxesInRange(x1, x2);
        openScene("main", findTaxesButton);
    }
}
