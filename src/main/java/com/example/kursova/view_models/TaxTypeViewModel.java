package com.example.kursova.view_models;

import javafx.beans.property.*;

public class TaxTypeViewModel {
    private IntegerProperty id;
    private StringProperty type;
    private DoubleProperty interest;

    public TaxTypeViewModel(int id, String type, double interest) {
        this.id = new SimpleIntegerProperty(id);
        this.type = new SimpleStringProperty(type);
        this.interest = new SimpleDoubleProperty(interest);
    }

    public TaxTypeViewModel() {

    }

    @Override
    public String toString() {
        return type.get() + " (" + interest.get() + "%)";
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public StringProperty typeProperty() {
        return type;
    }

    public double getInterest() {
        return interest.get();
    }

    public void setInterest(double interest) {
        this.interest.set(interest);
    }

    public DoubleProperty interestProperty() {
        return interest;
    }
}
