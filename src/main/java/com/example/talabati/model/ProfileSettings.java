package com.example.talabati.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "profile_settings")
public class ProfileSettings {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private boolean enableNotification;
    private String language;
        @JsonIgnore

    @OneToOne(mappedBy="profileSettings")
    private User user;
    private boolean isDarkMood;
    private long user_id;

    public ProfileSettings() {
    }
    
    public ProfileSettings(Long id, User user) {
        this.id = id;
        this.isDarkMood = false;
        this.enableNotification = true;
        this.language = "en";
        this.user = user;
    }

    public ProfileSettings(Long id, boolean isDarkMood, boolean enableNotification, String langauge,User user) {
        this.id = id;
        this.isDarkMood = isDarkMood;
        this.enableNotification = enableNotification;
        this.language = langauge;
        this.user = user;
        this.user_id=user.getId();

    }
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getUser_id() {
        return this.user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
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
    public String getLanguage() {
        return this.language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void updateFrom(ProfileSettings other) {
        if (other == null) {
            throw new IllegalArgumentException("The provided profile settings are null");
        }

        // Update fields if provided
        if (other.getEnableNotification() != this.enableNotification) {
            this.enableNotification = other.getEnableNotification();
        }

        if (other.getLanguage() != null && !other.getLanguage().isEmpty()) {
            this.language = other.getLanguage();
        }

        if (other.isIsDarkMood() != this.isDarkMood) {
            this.isDarkMood = other.isIsDarkMood();
        }

        // If you need to update the user, do it conditionally.
        // But this will be tricky if you want to avoid circular dependencies (User has ProfileSettings and ProfileSettings has User)
        if (other.getUser() != null && !other.getUser().equals(this.user)) {
            this.user = other.getUser();
        }
    }
}
