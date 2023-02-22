package com.example.kursova.store;

import com.example.kursova.view_models.TaxViewModel;

import java.util.ArrayList;

public class Store {
    public static int userId = 0;
    public static String userName = "";
    public static String userSurname = "";
    public static ArrayList<TaxViewModel> taxes = new ArrayList<>();
    public static double sumOfResultTaxes = 0.0;
    public static boolean isSearching = false;
}
