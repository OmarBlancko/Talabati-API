package com.example.talabati.model;

public class ProfileSettings {

    private int id;
    private boolean enableNotification;
    private String language;
    private int userId;
    private boolean isDarkMood;
    public ProfileSettings(int id, int userId) {
        this.id = id;
        this.isDarkMood = false;
        this.enableNotification = true;
        this.language = "en";
        this.userId = userId;
    }

    public ProfileSettings(int id, boolean isDarkMood, boolean enableNotification, String langauge, int userId) {
        this.id = id;
        this.isDarkMood = isDarkMood;
        this.enableNotification = enableNotification;
        this.language = langauge;
        this.userId = userId;
    }
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
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
