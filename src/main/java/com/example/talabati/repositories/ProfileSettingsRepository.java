package com.example.talabati.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.talabati.model.ProfileSettings;

@Repository
public interface ProfileSettingsRepository extends JpaRepository<ProfileSettings, Long> {
}
