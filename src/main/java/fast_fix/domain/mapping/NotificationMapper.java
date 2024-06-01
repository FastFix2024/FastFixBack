package fast_fix.domain.mapping;

import fast_fix.domain.dto.NotificationDto;
import fast_fix.domain.entity.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface NotificationMapper {

    NotificationDto toDto(Notification notification);

    Notification toEntity(NotificationDto notificationDto);
}