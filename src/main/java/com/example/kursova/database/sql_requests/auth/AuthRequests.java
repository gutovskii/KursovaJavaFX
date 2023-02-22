package com.example.kursova.database.sql_requests.auth;

public class AuthRequests {
    public static String getUserByLoginAndPasswordSql =
        AuthRequestsParts.selectUserByNameAndPassword +
        AuthRequestsParts.fromUsers +
        AuthRequestsParts.whereUsersLoginAndPassword;

    public static String getUserByLoginSql =
        AuthRequestsParts.selectUserByLogin +
        AuthRequestsParts.fromUsers +
        AuthRequestsParts.whereLogin;

    public static String insertUserSql =
        AuthRequestsParts.insertUser;
}
