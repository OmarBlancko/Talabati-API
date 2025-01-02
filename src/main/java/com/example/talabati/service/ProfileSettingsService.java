package com.example.talabati.service;

import org.springframework.stereotype.Service;
import com.example.talabati.repositories.ProfileSettingsRepository;

@Service
public class ProfileSettingsService {
    private final ProfileSettingsRepository profileSettingsRepository;

    public ProfileSettingsService(ProfileSettingsRepository profileSettingsRepository) {
        this.profileSettingsRepository = profileSettingsRepository;
    }
}
