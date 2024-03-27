package com.example.devoir.BD;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection con = null;
        con = DaoFactory.getConnection();

        if(con != null){
            System.out.println("database connection succesful");
        }
    }
}
