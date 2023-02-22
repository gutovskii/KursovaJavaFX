package com.example.kursova.database.sql_requests.tax;

public class TaxRequests {
    public static String getTaxesDataSql =
            TaxRequestsParts.selectTaxesDataFieldsSql +
            TaxRequestsParts.fromTaxesJoinTaxTypes +
            TaxRequestsParts.whereTaxes;

    public static String getSumOfResultTaxesSql =
            TaxRequestsParts.selectSumOfResultTaxesFieldsSql +
            TaxRequestsParts.fromTaxesJoinTaxTypes +
            TaxRequestsParts.whereTaxes;

    public static String getTaxesDataBySearchValueSql =
            TaxRequestsParts.selectTaxesDataFieldsSql +
            TaxRequestsParts.fromTaxesJoinTaxTypes +
            TaxRequestsParts.whereTaxesSearchValue;

    public static String getSumOfResultTaxesBySearchValueSql =
            TaxRequestsParts.selectSumOfResultTaxesFieldsSql +
            TaxRequestsParts.fromTaxesJoinTaxTypes +
            TaxRequestsParts.whereTaxesSearchValue;

    public static String getTaxesDataInRangeSql =
            TaxRequestsParts.selectTaxesDataFieldsSql +
            TaxRequestsParts.fromTaxesJoinTaxTypes +
            TaxRequestsParts.whereTaxesInRange;

    public static String getSumOfResultTaxesInRangeSql =
            TaxRequestsParts.selectSumOfResultTaxesFieldsSql +
            TaxRequestsParts.fromTaxesJoinTaxTypes +
            TaxRequestsParts.whereTaxesInRange;

    public static String createTaxSql =
            TaxRequestsParts.insertTax;

    public static String deleteTaxSql =
            TaxRequestsParts.deleteTax;
}
