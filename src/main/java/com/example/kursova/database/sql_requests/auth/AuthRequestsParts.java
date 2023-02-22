package com.example.kursova.database.sql_requests.auth;

import com.example.kursova.database.tables.UserTable;

public class AuthRequestsParts {
    public static String selectUserByNameAndPassword =
            "SELECT " + UserTable.ID + "," + UserTable.NAME + "," + UserTable.SURNAME + " ";

    public static String selectUserByLogin =
            "SELECT " + UserTable.LOGIN + " ";

    public static String fromUsers =
            "FROM " + UserTable.TABLE_NAME + " ";

    public static String whereUsersLoginAndPassword =
            "WHERE " + UserTable.LOGIN + " = ? AND " + UserTable.PASSWORD + " = ? ";

    public static String whereLogin =
            "WHERE " + UserTable.LOGIN + " = ? ";

    public static String insertUser =
            "INSERT INTO " + UserTable.TABLE_NAME + "(" +
            UserTable.NAME + "," + UserTable.SURNAME + "," +
            UserTable.LOGIN + "," + UserTable.PASSWORD
            + ")" + "VALUES (?,?,?,?)";

}
