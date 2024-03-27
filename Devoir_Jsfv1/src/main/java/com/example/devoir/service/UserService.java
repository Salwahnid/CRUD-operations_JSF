package com.example.devoir.service;
import com.example.devoir.BD.*;
import com.example.devoir.DAO.*;
import com.example.devoir.model.User;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import java.io.IOException;
import java.util.List;


public class UserService {
    private UserDao userDao;


    public UserService() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        userDao = daoFactory.getUserDao();
    }

    public UserService(UserDao userDao) {
       this.userDao = userDao;
   }

    public void addUser(User user) throws IOException {

        boolean added= userDao.ajouter(user);
        if (added){
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"la ligne a ete supprimer avec succees",""));
        }else {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"la ligne a ete supprimer avec succees",""));
        }

        redirectToHomePage();

    }

    public List<User> selectUsers(){
        return userDao.lister();
    }

    public boolean editUser(User user){
       return userDao.edit(user);
    }

    public void deleteUser(String email) throws IOException {

        boolean del= userDao.delete(email);
        if (del){
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"la ligne a ete supprimer avec succees",""));
        }else {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"la ligne a ete supprimer avec succees",""));
        }
        redirectToHomePage();
    }

    public void redirectToHomePage() throws IOException {

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException e) {
            e.printStackTrace(); // Gérer l'exception de redirection
        }
    }

    public boolean isExistEmail(User user){
        return userDao.existEmail(user);
    }

    public int getTotalUsersCount() {
        return userDao.usersNumber();
    }
}
