package com.example.kursova.database.sql_requests.tax;

import com.example.kursova.database.tables.TaxTable;
import com.example.kursova.database.tables.TaxTypeTable;

public class TaxRequestsParts {
    public static String selectSumOfResultTaxesFieldsSql =
            "SELECT " +
            "SUM(" + TaxTable.RESULT_TAX + ") AS sumOfResultTaxes ";

    public static String selectTaxesDataFieldsSql =
            "SELECT " +
            TaxTable.ID + "," + TaxTypeTable.TYPE + "," + TaxTable.SUM + "," +
            TaxTypeTable.INTEREST + "," + TaxTable.RESULT_TAX + " ";

    public static String fromTaxesJoinTaxTypes =
            "FROM taxes " +
            "JOIN tax_types ON " + TaxTable.TAX_TYPE_ID + "=" + TaxTypeTable.ID + " ";

    public static String whereTaxes =
            "WHERE " + TaxTable.USER_ID + "= ? ORDER BY " + TaxTable.ID;

    public static String whereTaxesSearchValue =
            "WHERE " + TaxTable.USER_ID + "= ? AND (" +
            TaxTable.ID + " LIKE ? OR " +
            TaxTypeTable.TYPE + " LIKE ? OR " +
            TaxTable.SUM + " LIKE ? OR " +
            TaxTypeTable.INTEREST + " LIKE ? OR " +
            TaxTable.RESULT_TAX + " LIKE ?) " +
            "ORDER BY " + TaxTable.ID;

    public static String whereTaxesInRange =
            "WHERE " + TaxTable.USER_ID + "= ? " +
            "AND " + TaxTable.RESULT_TAX + " > ? AND " + TaxTable.RESULT_TAX + " < ? " +
            "ORDER BY " + TaxTable.ID;

    public static String insertTax =
            "INSERT INTO " + TaxTable.TABLE_NAME + "(" +
            TaxTable.SUM + "," + TaxTable.RESULT_TAX + "," +
            TaxTable.TAX_TYPE_ID + "," + TaxTable.USER_ID
            + ")" + "VALUES (?,?,?,?)";

    public static String deleteTax =
            "DELETE FROM taxes WHERE id = ?";
}
