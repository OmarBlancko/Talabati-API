package com.example.talabati.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.talabati.Exceptions.ProfileSettingsNotFound;
import com.example.talabati.model.ProfileSettings;
import com.example.talabati.repositories.ProfileSettingsRepository;

@Service
public class ProfileSettingsService {

    @Autowired
    private final ProfileSettingsRepository profileSettingsRepository;

    public ProfileSettingsService(ProfileSettingsRepository profileSettingsRepository) {
        this.profileSettingsRepository = profileSettingsRepository;
    }

    public ProfileSettings createProfileSettings(ProfileSettings profileSettings) {
        if (profileSettings == null) {
            throw new IllegalArgumentException("Profile Settings cannot be null");

        }

        return profileSettingsRepository.save(profileSettings);
    }
    public ProfileSettings  updateSettings(ProfileSettings updatedSettings,Long id) {
        if (updatedSettings == null) {
            throw new IllegalArgumentException("Profile Settings cannot be null");

        }
        ProfileSettings existingSettings = getById(id);
        checkRequestValidity(updatedSettings, existingSettings);
        existingSettings.updateFrom(updatedSettings);
        return profileSettingsRepository.save(existingSettings);
        
         
    }
    public ProfileSettings getById(Long id) {
        Optional<ProfileSettings> settings = profileSettingsRepository.findById(id);
        if(settings == null || settings.isEmpty()) {
            throw   new  ProfileSettingsNotFound("No settings found");
        }
        else {
            return settings.get();
        }
    }
    public void checkRequestValidity(ProfileSettings updatedProfileSettings, ProfileSettings existingProfileSettings) {
        // Check if the enableNotification is provided and update it
        if (updatedProfileSettings.getEnableNotification() != existingProfileSettings.getEnableNotification()) {
            existingProfileSettings.setEnableNotification(updatedProfileSettings.getEnableNotification());
        }
    
        // Check if the language is not null or empty and update it
        if (updatedProfileSettings.getLanguage() != null && !updatedProfileSettings.getLanguage().isEmpty()) {
            existingProfileSettings.setLanguage(updatedProfileSettings.getLanguage());
        }
    
        // Check if isDarkMood is provided and update it
        if (updatedProfileSettings.isIsDarkMood() != existingProfileSettings.isIsDarkMood()) {
            existingProfileSettings.setIsDarkMood(updatedProfileSettings.isIsDarkMood());
        }
    
        // Check if the user is provided and update if needed (this can be tricky due to circular dependency, avoid updating this unless necessary)
        if (updatedProfileSettings.getUser() != null && !updatedProfileSettings.getUser().equals(existingProfileSettings.getUser())) {
            existingProfileSettings.setUser(updatedProfileSettings.getUser());
        }
    }
    
}
