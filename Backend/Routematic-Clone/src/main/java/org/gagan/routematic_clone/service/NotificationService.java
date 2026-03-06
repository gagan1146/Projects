package org.gagan.routematic_clone.service;

import org.gagan.routematic_clone.model.Notification;
import org.gagan.routematic_clone.repository.NotificationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public ResponseEntity<Notification> createNotification(Notification notification) {
        notificationRepository.save(notification);
        return ResponseEntity.ok(notification);
    }

    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notifications = notificationRepository.findAll();
        return ResponseEntity.ok(notifications);
    }

    public ResponseEntity<Notification> getNotificationById(UUID id) {
        Optional<Notification> notification = notificationRepository.findById(id);
        return notification.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Notification> updateNotification(UUID id, Notification updatedNotification) {
        Optional<Notification> notificationOptional = notificationRepository.findById(id);
        if (notificationOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Notification notification = notificationOptional.get();
        notification.setRecipientEmail(updatedNotification.getRecipientEmail());
        notification.setSubject(updatedNotification.getSubject());
        notification.setMessage(updatedNotification.getMessage());
        notification.setSendAt(updatedNotification.getSendAt());
        notification.setStatus(updatedNotification.getStatus());
        notificationRepository.save(notification);
        return ResponseEntity.ok(notification);
    }

    public ResponseEntity<Void> deleteNotification(UUID id) {
        Optional<Notification> notificationOptional = notificationRepository.findById(id);
        if (notificationOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        notificationRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}