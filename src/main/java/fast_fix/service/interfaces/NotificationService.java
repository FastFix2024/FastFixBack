package fast_fix.service.interfaces;

import fast_fix.domain.dto.NotificationDto;

import java.util.List;

public interface NotificationService {
    List<NotificationDto> getAllNotificationsForUser(Long userId);
    NotificationDto getNotificationById(Long id);
    void deleteNotification(Long id);
}