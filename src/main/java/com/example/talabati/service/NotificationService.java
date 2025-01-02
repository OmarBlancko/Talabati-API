package com.example.talabati.service;

import org.springframework.stereotype.Service;
import com.example.talabati.repositories.NotificationRepository;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
}
