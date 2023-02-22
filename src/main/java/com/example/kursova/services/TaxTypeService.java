package com.example.kursova.services;

import com.example.kursova.database.DbHandler;
import com.example.kursova.database.sql_requests.tax_type.TaxTypeRequests;
import com.example.kursova.database.tables.TaxTypeTable;
import com.example.kursova.view_models.TaxTypeViewModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TaxTypeService {
    public ArrayList<TaxTypeViewModel> getTaxTypes() {
        var taxTypeViewModelList = new ArrayList<TaxTypeViewModel>();
        try {
            Statement statement = DbHandler.getConnection().createStatement();
            ResultSet rsTaxTypesData = statement.executeQuery(TaxTypeRequests.getTaxTypesSql);
            while (rsTaxTypesData.next()) {
                taxTypeViewModelList.add(new TaxTypeViewModel(
                        rsTaxTypesData.getInt(TaxTypeTable.ID),
                        rsTaxTypesData.getString(TaxTypeTable.TYPE),
                        rsTaxTypesData.getDouble(TaxTypeTable.INTEREST)
                ));
            }
            return taxTypeViewModelList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
