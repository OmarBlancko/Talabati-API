package com.example.talabati.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProfileSettings {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private boolean enableNotification;
    private String language;
    private int userId;
    private boolean isDarkMood;
    public ProfileSettings(Long id, int userId) {
        this.id = id;
        this.isDarkMood = false;
        this.enableNotification = true;
        this.language = "en";
        this.userId = userId;
    }

    public ProfileSettings(Long id, boolean isDarkMood, boolean enableNotification, String langauge, int userId) {
        this.id = id;
        this.isDarkMood = isDarkMood;
        this.enableNotification = enableNotification;
        this.language = langauge;
        this.userId = userId;
    }
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isIsDarkMood() {
        return this.isDarkMood;
    }

    public boolean getIsDarkMood() {
        return this.isDarkMood;
    }

    public void setIsDarkMood(boolean isDarkMood) {
        this.isDarkMood = isDarkMood;
    }

    public boolean isEnableNotification() {
        return this.enableNotification;
    }

    public boolean getEnableNotification() {
        return this.enableNotification;
    }

    public void setEnableNotification(boolean enableNotification) {
        this.enableNotification = enableNotification;
    }

    public String getLangauge() {
        return this.language;
    }

    public void setLangauge(String langauge) {
        this.language = langauge;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


}
