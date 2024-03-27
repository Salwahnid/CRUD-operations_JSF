package com.example.devoir.DAO;
import com.example.devoir.model.*;

import java.util.List;

public interface UserDao {
    boolean ajouter(User user);
    List<User> lister();


    boolean edit(User user);

    boolean delete(String email);

    boolean existEmail(User user);

    int usersNumber();


}
