package fast_fix.controller;

import fast_fix.domain.dto.NotificationDto;
import fast_fix.service.interfaces.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationDto>> getAllNotificationsForUser(@PathVariable Long userId) {
        List<NotificationDto> notifications = notificationService.getAllNotificationsForUser(userId);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDto> getNotificationById(@PathVariable Long id) {
        NotificationDto notification = notificationService.getNotificationById(id);
        if (notification != null) {
            return ResponseEntity.ok(notification);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<NotificationDto> createNotification(@RequestBody NotificationDto notificationDto) {
        NotificationDto createdNotification = notificationService.createNotification(notificationDto);
        return ResponseEntity.ok(createdNotification);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationDto> updateNotification(@PathVariable Long id, @RequestBody NotificationDto notificationDto) {
        NotificationDto updatedNotification = notificationService.updateNotification(id, notificationDto);
        if (updatedNotification != null) {
            return ResponseEntity.ok(updatedNotification);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}