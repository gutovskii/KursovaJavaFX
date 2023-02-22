package com.example.kursova.dtos;

public class TaxDto {
    private double sum;
    private double resultTax;
    private int taxTypeId;
    private int userId;

    public TaxDto(double sum, double resultTax, int taxTypeId, int userId) {
        this.sum = sum;
        this.resultTax = resultTax;
        this.taxTypeId = taxTypeId;
        this.userId = userId;
    }

    public TaxDto() {

    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getResultTax() {
        return resultTax;
    }

    public void setResultTax(double resultTax) {
        this.resultTax = resultTax;
    }

    public int getTaxTypeId() {
        return taxTypeId;
    }

    public void setTaxTypeId(int taxTypeId) {
        this.taxTypeId = taxTypeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
