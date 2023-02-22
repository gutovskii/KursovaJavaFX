package com.example.kursova.services;

import com.example.kursova.database.DbHandler;
import com.example.kursova.database.tables.TaxTypeTable;
import com.example.kursova.dtos.TaxDto;
import com.example.kursova.database.sql_requests.tax.TaxRequests;
import com.example.kursova.store.Store;
import com.example.kursova.database.tables.TaxTable;
import com.example.kursova.view_models.TaxViewModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TaxService {
    public ArrayList<TaxViewModel> findAllTaxes() {
        try {
            PreparedStatement getSumOfResultTaxesPs = DbHandler.getConnection().prepareStatement(TaxRequests.getSumOfResultTaxesSql);
            getSumOfResultTaxesPs.setInt(1, Store.userId);
            ResultSet getSumOfResultTaxesRs = getSumOfResultTaxesPs.executeQuery();
            getSumOfResultTaxesRs.next();
            Store.sumOfResultTaxes = round(getSumOfResultTaxesRs.getDouble(1), 2);

            PreparedStatement getTaxesDataPs = DbHandler.getConnection().prepareStatement(TaxRequests.getTaxesDataSql);
            getTaxesDataPs.setInt(1, Store.userId);
            return getTaxViewModelsInStatement(getTaxesDataPs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<TaxViewModel> findTaxesBySearchValue(String searchValue) {
        try {
            String likeStatement = "%" + searchValue + "%";

            PreparedStatement getSumOfResultTaxesPs = DbHandler.getConnection().prepareStatement(TaxRequests.getSumOfResultTaxesBySearchValueSql);
            getSumOfResultTaxesPs.setInt(1, Store.userId);
            getSumOfResultTaxesPs.setString(2, likeStatement);
            getSumOfResultTaxesPs.setString(3, likeStatement);
            getSumOfResultTaxesPs.setString(4, likeStatement);
            getSumOfResultTaxesPs.setString(5, likeStatement);
            getSumOfResultTaxesPs.setString(6, likeStatement);
            ResultSet getSumOfResultTaxesRs = getSumOfResultTaxesPs.executeQuery();
            getSumOfResultTaxesRs.next();
            Store.sumOfResultTaxes = round(getSumOfResultTaxesRs.getDouble(1), 2);

            PreparedStatement getTaxesDataPs = DbHandler.getConnection().prepareStatement(TaxRequests.getTaxesDataBySearchValueSql);
            getTaxesDataPs.setInt(1, Store.userId);
            getTaxesDataPs.setString(2, likeStatement);
            getTaxesDataPs.setString(3, likeStatement);
            getTaxesDataPs.setString(4, likeStatement);
            getTaxesDataPs.setString(5, likeStatement);
            getTaxesDataPs.setString(6, likeStatement);
            return getTaxViewModelsInStatement(getTaxesDataPs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<TaxViewModel> findTaxesInRange(double x1, double x2) {
        try {
            PreparedStatement getSumOfResultTaxesPs = DbHandler.getConnection().prepareStatement(TaxRequests.getSumOfResultTaxesInRangeSql);
            getSumOfResultTaxesPs.setInt(1, Store.userId);
            getSumOfResultTaxesPs.setDouble(2, x1);
            getSumOfResultTaxesPs.setDouble(3, x2);
            ResultSet getSumOfResultTaxesRs = getSumOfResultTaxesPs.executeQuery();
            getSumOfResultTaxesRs.next();
            Store.sumOfResultTaxes = round(getSumOfResultTaxesRs.getDouble(1), 2);

            PreparedStatement getTaxesDataPs = DbHandler.getConnection().prepareStatement(TaxRequests.getTaxesDataInRangeSql);
            getTaxesDataPs.setInt(1, Store.userId);
            getTaxesDataPs.setDouble(2, x1);
            getTaxesDataPs.setDouble(3, x2);
            return getTaxViewModelsInStatement(getTaxesDataPs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTax(TaxDto tax) {
        try {
            PreparedStatement psCreateTax = DbHandler.getConnection().prepareStatement(TaxRequests.createTaxSql);
            psCreateTax.setDouble(1, tax.getSum());
            psCreateTax.setDouble(2, tax.getResultTax());
            psCreateTax.setInt(3, tax.getTaxTypeId());
            psCreateTax.setInt(4, tax.getUserId());
            psCreateTax.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTax(int id) {
        try {
            PreparedStatement psDeleteTax = DbHandler.getConnection().prepareStatement(TaxRequests.deleteTaxSql);
            psDeleteTax.setInt(1, id);
            psDeleteTax.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<TaxViewModel> getTaxViewModelsInStatement(PreparedStatement preparedStatement) throws SQLException {
        var taxViewModelsList = new ArrayList<TaxViewModel>();
        ResultSet rsTableData = preparedStatement.executeQuery();
        while (rsTableData.next()) {
            taxViewModelsList.add(new TaxViewModel(
                    rsTableData.getInt(TaxTable.ID),
                    rsTableData.getString(TaxTypeTable.TYPE),
                    rsTableData.getDouble(TaxTable.SUM),
                    rsTableData.getDouble(TaxTypeTable.INTEREST),
                    rsTableData.getDouble(TaxTable.RESULT_TAX)
            ));
        }
        return taxViewModelsList;
    }

    private double round(Double value, int places) {
        return new BigDecimal(value.toString()).setScale(places, RoundingMode.HALF_UP).doubleValue();
    }
}
