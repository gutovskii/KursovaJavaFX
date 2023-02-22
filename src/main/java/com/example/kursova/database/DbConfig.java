package com.example.kursova.database;

public class DbConfig {
    public static String dbHost = "localhost";
    public static String dbPort = "3306";
    public static String dbName = "kursova";
    public static String dbUser = "root";
    public static String dbPass = "";
    public static String connectionString =
            "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
}
