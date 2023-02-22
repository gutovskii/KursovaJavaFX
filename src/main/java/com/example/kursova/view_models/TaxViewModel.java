package com.example.kursova.view_models;

import javafx.beans.property.*;

public class TaxViewModel {
    private IntegerProperty id;
    private StringProperty type;
    private DoubleProperty sum;
    private DoubleProperty interest;
    private DoubleProperty resultTax;

    public TaxViewModel(int id, String type, double sum, double interest, double resultTax) {
        this.id = new SimpleIntegerProperty(id);
        this.type = new SimpleStringProperty(type);
        this.sum = new SimpleDoubleProperty(sum);
        this.interest = new SimpleDoubleProperty(interest);
        this.resultTax = new SimpleDoubleProperty(resultTax);
    }

    public TaxViewModel() {

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

    public double getSum() {
        return sum.get();
    }

    public void setSum(double sum) {
        this.sum.set(sum);
    }

    public DoubleProperty sumProperty() {
        return sum;
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

    public double getResultTax() {
        return resultTax.get();
    }

    public void setResultTax(double resultTax) {
        this.resultTax.set(resultTax);
    }

    public DoubleProperty resultTaxProperty() {
        return resultTax;
    }
}
