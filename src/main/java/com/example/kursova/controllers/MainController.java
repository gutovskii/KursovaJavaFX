package com.example.kursova.controllers;

import com.example.kursova.services.TaxService;
import com.example.kursova.store.Store;
import com.example.kursova.view_models.TaxViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

public class MainController extends Controller {

    @FXML
    private Button mainAddTaxButton;

    @FXML
    private Button mainFindTaxesInRangeButton;

    @FXML
    private Button mainDeleteTaxButton;

    @FXML
    private Button mainRefreshTaxesButton;

    @FXML
    private Button mainFindTaxesBySearchValueButton;

    @FXML
    private TextField mainFindTaxesBySearchValueField;

    @FXML
    private Button mainLogoutButton;

    @FXML
    private Label mainHelloLabel;

    @FXML
    private Label mainTaxResultSumLabel;

    @FXML
    private TableView<TaxViewModel> mainTaxTable;

    @FXML
    private TableColumn<TaxViewModel, Integer> mainIdColumn;

    @FXML
    private TableColumn<TaxViewModel, String> mainTaxTypeColumn;

    @FXML
    private TableColumn<TaxViewModel, Double> mainUserSumColumn;

    @FXML
    private TableColumn<TaxViewModel, Double> mainTaxInterestColumn;

    @FXML
    private TableColumn<TaxViewModel, Double> mainResultTaxColumn;

    private final TaxService taxService = new TaxService();

    @FXML
    public void initialize() {
        mainHelloLabel.setText("Привіт, " + Store.userName + " " + Store.userSurname);
        setTableColumns();
        refreshTable();

        mainAddTaxButton.setOnAction(event -> openScene("createTax", mainAddTaxButton));
        mainDeleteTaxButton.setOnAction(event -> deleteTax());
        mainFindTaxesInRangeButton.setOnAction(event -> openScene("findTaxesInRange", mainAddTaxButton));
        mainFindTaxesBySearchValueButton.setOnAction(event -> findTaxesBySearchValue());

        mainRefreshTaxesButton.setOnAction(event -> {
            if (!Store.isSearching) return;
            Store.isSearching = false;
            refreshTable();
        });
        mainLogoutButton.setOnAction(event -> {
            openScene("login", mainLogoutButton);
            clearStore();
        });
    }

    private void findTaxesBySearchValue() {
        if (mainFindTaxesBySearchValueField.getText().isBlank() && !Store.isSearching) return;
        else if (mainFindTaxesBySearchValueField.getText().isBlank() && Store.isSearching) {
            Store.isSearching = false;
            refreshTable();
            return;
        }
        Store.isSearching = true;
        Store.taxes = taxService.findTaxesBySearchValue(mainFindTaxesBySearchValueField.getText());
        refreshTable();
    }

    private void deleteTax() {
        int selectedTaxId = mainTaxTable.getSelectionModel().getSelectedItem().getId();
        taxService.deleteTax(selectedTaxId);
        if (Store.isSearching) {
            Store.taxes.removeIf(tax -> tax.getId() == selectedTaxId);
            Store.sumOfResultTaxes = Store.taxes.stream().reduce(
                    0.0,
                    (sum, tax) -> {
                        return sum + tax.getResultTax();
                    }, Double::sum);
        }
        refreshTable();
    }

    private void refreshTable() {
        ArrayList<TaxViewModel> taxViewModels = Store.isSearching ? Store.taxes : taxService.findAllTaxes();
        ObservableList<TaxViewModel> tableData = FXCollections.observableArrayList(taxViewModels);
        mainTaxTable.setItems(tableData);
        mainTaxResultSumLabel.setText("Сума податків: " + Store.sumOfResultTaxes);
    }

    private void setTableColumns() {
        mainIdColumn.setCellValueFactory(data -> data.getValue().idProperty().asObject());
        mainTaxTypeColumn.setCellValueFactory(data -> data.getValue().typeProperty());
        mainUserSumColumn.setCellValueFactory(data -> data.getValue().sumProperty().asObject());
        mainTaxInterestColumn.setCellValueFactory(data -> data.getValue().interestProperty().asObject());
        mainResultTaxColumn.setCellValueFactory(data -> data.getValue().resultTaxProperty().asObject());
    }
}
