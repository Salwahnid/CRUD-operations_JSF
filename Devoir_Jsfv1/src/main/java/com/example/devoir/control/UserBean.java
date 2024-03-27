package com.example.devoir.control;

import com.example.devoir.DAO.UserDaoImp;
import com.example.devoir.model.User;
import com.example.devoir.service.UserService;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class UserBean {
    private User user ;
    private UserService userService;
    private List<User> users;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserBean() {
        users = new ArrayList<>();
        UserService userService = new UserService();
        users = userService.selectUsers();
        this.user= new User();
    }
    public List<User> getUsers() {
        return users;
    }


    public void deleteuser(String email) throws IOException {

        UserService userService = new UserService();
        userService.deleteUser(email);
    }

    public void adduser(User user) throws IOException {
        UserService userService = new UserService();
        userService.addUser(user);
    }

    public void addRow(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("addRow.xhtml");
        } catch (IOException e) {
            e.printStackTrace(); // Gérer l'exception de redirection
        }
    }

    private int firstRowIndex = 0;
    private int rowsPerPageS = 10; // Nombre de lignes par page

    public int getTotalUsersCount() {
        // Implémentez cette méthode pour obtenir le nombre total d'utilisateurs
        // depuis votre source de données (par exemple, votre DAO)
        UserService userService = new UserService();
        return userService.getTotalUsersCount();
    }


    public int getFirstRowIndex() {
        return firstRowIndex;
    }

    public void setFirstRowIndex(int firstRowIndex) {
        this.firstRowIndex = firstRowIndex;
    }

    public void goToPage(int pageNumber) {
        firstRowIndex = (pageNumber - 1) * rowsPerPage;
    }


    public int getRowsPerPage() {
        return rowsPerPage;
    }
    private int rowsPerPage =6;
    private int currentPage = 1;
    private int totalRows = 0;
    private boolean previousButtonDisabled;
    private boolean nextButtonDisabled;
    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }
    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public boolean isPreviousButtonDisabled() {
        return previousButtonDisabled;
    }

    public void setPreviousButtonDisabled(boolean previousButtonDisabled) {
        this.previousButtonDisabled = previousButtonDisabled;
    }

    public boolean isNextButtonDisabled() {
        return nextButtonDisabled;
    }

    public void setNextButtonDisabled(boolean nextButtonDisabled) {
        this.nextButtonDisabled = nextButtonDisabled;
    }

    public void previousPage() {
        if (currentPage > 1) {
            currentPage--;
            updateButtonDisabledState();
        }
    }

    public void nextPage() {
        int lastPage = (int) Math.ceil((double) totalRows / rowsPerPage);
        if (currentPage < lastPage) {
            currentPage++;
            updateButtonDisabledState();
        }
    }
    private void updateButtonDisabledState() {
        previousButtonDisabled = currentPage <= 1;

        int lastPage = (int) Math.ceil((double) totalRows / rowsPerPage);
        nextButtonDisabled = currentPage >= lastPage;
    }

}
