package fast_fix.service.interfaces;

import fast_fix.domain.dto.NotificationDto;

import java.util.List;

public interface NotificationService {
    List<NotificationDto> getAllNotificationsForUser(Long userId);
    NotificationDto getNotificationById(Long id);
    NotificationDto createNotification(NotificationDto notificationDto);
    NotificationDto updateNotification(Long id, NotificationDto notificationDto);
    void deleteNotification(Long id);
}