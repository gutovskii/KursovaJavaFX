package com.example.kursova.database.sql_requests.tax_type;

import com.example.kursova.database.tables.TaxTypeTable;

public class TaxTypeRequestsParts {
    public static String selectTaxTypes =
            "SELECT " +
            TaxTypeTable.ID + "," + TaxTypeTable.TYPE + "," + TaxTypeTable.INTEREST + " ";

    public static String fromTaxTypes =
            "FROM " + TaxTypeTable.TABLE_NAME;
}
