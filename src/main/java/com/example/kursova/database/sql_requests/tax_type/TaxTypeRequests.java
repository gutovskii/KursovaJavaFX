package com.example.kursova.database.sql_requests.tax_type;

public class TaxTypeRequests {
    public static String getTaxTypesSql =
            TaxTypeRequestsParts.selectTaxTypes +
            TaxTypeRequestsParts.fromTaxTypes;
}
