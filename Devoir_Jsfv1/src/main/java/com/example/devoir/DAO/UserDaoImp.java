package com.example.devoir.DAO;
import com.example.devoir.BD.DaoFactory;
import com.example.devoir.model.*;
import com.example.devoir.service.UserService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImp implements UserDao {
    private DaoFactory daoFactory;

    public UserDaoImp(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public UserDaoImp() {

    }

    public boolean ajouter(User user) {
        boolean i = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DaoFactory.getConnection();
            java.util.Date utilDate = user.getBirthDate();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            preparedStatement = connection.prepareStatement("insert into user_(nom,prenom,ville,email,date_naissance) values(?,?,?,?,?)");
            preparedStatement.setString(1, user.getLastName());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getCity());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setDate(5, sqlDate);

            preparedStatement.executeUpdate();
            i = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return i;
    }


    public List<User> lister() {
        UserService userService = new UserService();
        List<User> users = new ArrayList<User>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connection = DaoFactory.getConnection();
            statement = connection.createStatement();
            resultat = statement.executeQuery("select nom, prenom, ville, email, date_naissance from user_;");

            while (resultat.next()) {
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                String ville = resultat.getString("ville");
                String email = resultat.getString("email");
                Date date = resultat.getDate("date_naissance");

                User user = new User();
                user.setLastName(nom);
                user.setFirstName(prenom);
                user.setCity(ville);
                user.setEmail(email);
                user.setBirthDate(date);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public boolean edit(User user) {
        try {
            Connection connection = DaoFactory.getConnection();
            String sql = "update user_ set nom = ?, prenom=?, ville=?, email=?, date_naissance=? where id = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, user.getLastName());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getCity());
            ps.setString(4, user.getEmail());
            ps.setDate(5, (Date) user.getBirthDate());
            ps.setInt(6, user.getId());

            int result = ps.executeUpdate();
            if (result == 1) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean delete(String email1) {
        try {
            Connection connection = DaoFactory.getConnection();
            String sql = "delete from user_ WHERE email = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email1);


            int result = ps.executeUpdate();
            if (result == 1) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean existEmail(User user) {
        try {
            Connection connection = DaoFactory.getConnection();
            String sql = "SELECT COUNT(*) FROM user_ WHERE email=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            int result = ps.executeUpdate();

            if (result == 1) {
                return true;
            }
            return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int usersNumber(){
        try{
            Connection connection = DaoFactory.getConnection();
            String req= "SELECT COUNT(*) FROM user_";
            PreparedStatement ps = connection.prepareStatement(req);
            int result = ps.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}


