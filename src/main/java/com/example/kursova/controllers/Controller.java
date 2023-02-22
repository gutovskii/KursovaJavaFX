package com.example.kursova.controllers;

import com.example.kursova.TaxApplication;
import com.example.kursova.store.Store;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public abstract class Controller {
    public void openScene(String sceneName, Node node) {
        node.getScene().getWindow().hide();
        var loader = new FXMLLoader();
        loader.setLocation(TaxApplication.class.getResource(sceneName + ".fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Податкова база");
        stage.getIcons().add(new Image(Objects.requireNonNull(TaxApplication.class.getResourceAsStream("assets/AzovEmblem.png"))));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void clearStore() {
        Store.userId = 0;
        Store.userName = "";
        Store.userSurname = "";
        Store.isSearching = false;
        Store.sumOfResultTaxes = 0.0;
        Store.taxes.clear();
    }
}
