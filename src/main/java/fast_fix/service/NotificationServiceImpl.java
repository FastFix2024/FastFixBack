package fast_fix.service;

import fast_fix.domain.dto.NotificationDto;
import fast_fix.domain.entity.Notification;
import fast_fix.domain.mapping.NotificationMapper;
import fast_fix.repository.NotificationRepository;
import fast_fix.service.interfaces.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    public List<NotificationDto> getAllNotificationsForUser(Long userId) {
        List<Notification> notifications = notificationRepository.findAllByUserId(userId);
        return notifications.stream().map(notificationMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public NotificationDto getNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id).orElse(null);
        return notification != null ? notificationMapper.toDto(notification) : null;
    }

    @Override
    public NotificationDto createNotification(NotificationDto notificationDto) {
        Notification notification = notificationMapper.toEntity(notificationDto);
        notification = notificationRepository.save(notification);
        return notificationMapper.toDto(notification);
    }

    @Override
    public NotificationDto updateNotification(Long id, NotificationDto notificationDto) {
        Notification notification = notificationRepository.findById(id).orElse(null);
        if (notification != null) {
            notification.setMessage(notificationDto.getMessage());
            notification.setType(notificationDto.getType());
            notification.setCreatedAt(notificationDto.getCreatedAt());
            notification = notificationRepository.save(notification);
            return notificationMapper.toDto(notification);
        }
        return null;
    }

    @Override
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}