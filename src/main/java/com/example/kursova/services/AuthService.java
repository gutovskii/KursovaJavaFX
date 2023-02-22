package com.example.kursova.services;

import com.example.kursova.database.DbHandler;
import com.example.kursova.database.sql_requests.auth.AuthRequests;
import com.example.kursova.dtos.UserDto;
import com.example.kursova.store.Store;
import com.example.kursova.database.tables.UserTable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthService {
    public boolean login(String login, String password) {
        try {
            PreparedStatement ps = DbHandler.getConnection().prepareStatement(AuthRequests.getUserByLoginAndPasswordSql);
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();
            if (!resultSet.next()) return false;
            Store.userId = resultSet.getInt(UserTable.ID);
            Store.userName = resultSet.getString(UserTable.NAME);
            Store.userSurname = resultSet.getString(UserTable.SURNAME);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean register(UserDto user) {
        try {
            PreparedStatement psSelectUser = DbHandler.getConnection().prepareStatement(AuthRequests.getUserByLoginSql);
            psSelectUser.setString(1, user.getLogin());
            ResultSet rsSelectUser = psSelectUser.executeQuery();
            if (rsSelectUser.isBeforeFirst()) return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            PreparedStatement psCreateUser = DbHandler.getConnection().prepareStatement(AuthRequests.insertUserSql);
            psCreateUser.setString(1, user.getName());
            psCreateUser.setString(2, user.getSurname());
            psCreateUser.setString(3, user.getLogin());
            psCreateUser.setString(4, user.getPassword());
            psCreateUser.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
