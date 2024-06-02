package fast_fix.service;

import fast_fix.domain.dto.NotificationDto;
import fast_fix.domain.entity.Notification;
import fast_fix.domain.mapping.NotificationMapper;
import fast_fix.exceptions.ResourceNotFoundException;
import fast_fix.repository.NotificationRepository;
import fast_fix.service.interfaces.NotificationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    private NotificationRepository notificationRepository;

    private NotificationMapper notificationMapper;

    @Override
    public List<NotificationDto> getAllNotificationsForUser(Long userId) {
        List<Notification> notifications = notificationRepository.findAllByUserId(userId);
        return notifications.stream().map(notificationMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public NotificationDto getNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found"));
        return notificationMapper.toDto(notification);
    }

    @Override
    public void deleteNotification(Long id) {
        if (!notificationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Notification not found");
        }
        notificationRepository.deleteById(id);
    }
}