package com.example.kursova.controllers;

import com.example.kursova.dtos.TaxDto;
import com.example.kursova.services.TaxService;
import com.example.kursova.services.TaxTypeService;
import com.example.kursova.store.Store;
import com.example.kursova.view_models.TaxTypeViewModel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class CreateTaxController extends Controller {

    @FXML
    private Button createTaxButton;

    @FXML
    private ChoiceBox<TaxTypeViewModel> createTaxFormType;

    @FXML
    private TextField createTaxFormUserSum;

    @FXML
    private Label createTaxSuccessMessage;

    @FXML
    private Label createTaxErrorMessage;

    @FXML
    private Button createTaxGoBack;

    private final TaxService taxService = new TaxService();
    private final TaxTypeService taxTypeService = new TaxTypeService();

    @FXML
    public void initialize() {
        loadTaxTypesData();
        createTaxButton.setOnAction(event -> createTax());
        createTaxGoBack.setOnAction(event -> openScene("main", createTaxGoBack));
    }

    private void createTax() {
        if (createTaxFormUserSum.getText().isBlank() || createTaxFormType.getValue() == null) {
            createTaxErrorMessage.setText("Заповніть усі поля");
            return;
        }
        double sum = round(Double.parseDouble(createTaxFormUserSum.getText()), 2);
        if (sum <= 0) {
            createTaxErrorMessage.setText("Сума має бути більше нуля");
            return;
        }
        double resultTax = round(sum / 100 * createTaxFormType.getValue().getInterest(), 2);
        int taxTypeId = createTaxFormType.getValue().getId();
        int userId = Store.userId;
        taxService.createTax(new TaxDto(sum, resultTax, taxTypeId, userId));
        createTaxErrorMessage.setText("");
        createTaxSuccessMessage.setText("Податок додано успішно!");
        Timeline removeSuccessMessageTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1.5), event -> createTaxSuccessMessage.setText(""))
        );
        removeSuccessMessageTimeline.play();
    }

    private void loadTaxTypesData() {
        ArrayList<TaxTypeViewModel> taxTypes = taxTypeService.getTaxTypes();
        ObservableList<TaxTypeViewModel> choiceBoxTaxTypesData = FXCollections.observableArrayList(taxTypes);
        createTaxFormType.setItems(choiceBoxTaxTypesData);
    }

    private double round(Double value, int places) {
        return new BigDecimal(value.toString()).setScale(places, RoundingMode.HALF_UP).doubleValue();
    }
}
