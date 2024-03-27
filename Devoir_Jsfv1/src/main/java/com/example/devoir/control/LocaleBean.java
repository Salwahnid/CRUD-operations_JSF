package com.example.devoir.control;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@ManagedBean(name = "localeBean")
@SessionScoped
@ViewScoped
public class LocaleBean implements Serializable {
    private String selectedLanguage;
    private final List<Locale> supportedLanguages;

    public LocaleBean() {
        // Initialisation des langues supportées
        supportedLanguages = new ArrayList<>();
        supportedLanguages.add(new Locale("fr"));
        supportedLanguages.add(new Locale("en"));

        // Langue par défaut
        selectedLanguage = "fr"; // Langue par défaut
    }

    public String getSelectedLanguage() {
        return selectedLanguage;
    }

    public void setSelectedLanguage(String selectedLanguage) {
        this.selectedLanguage = selectedLanguage;
    }

    public List<Locale> getSupportedLanguages() {
        return supportedLanguages;
    }

    public void changeLanguage() {
        // Mettre à jour la locale de la vue avec la langue sélectionnée
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(selectedLanguage));
    }
}

