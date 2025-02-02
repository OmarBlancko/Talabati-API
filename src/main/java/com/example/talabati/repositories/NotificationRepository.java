package com.example.talabati.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.talabati.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
