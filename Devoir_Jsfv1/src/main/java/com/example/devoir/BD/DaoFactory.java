package com.example.devoir.BD;

import com.example.devoir.DAO.UserDao;
import com.example.devoir.DAO.UserDaoImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
    private static String url;
    private static String username;
    private static String password;

    public DaoFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DaoFactory getInstance(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        DaoFactory instance = new DaoFactory("jdbc:mysql://localhost:3306/devoir_db", "root","");
        return instance;
    }

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, username, password);
    }

    public UserDao getUserDao(){
        return new UserDaoImp(this);
    }
}
