package fast_fix.service;

import fast_fix.domain.entity.CarDetails;
import fast_fix.domain.entity.User;
import fast_fix.repository.UserRepository;
import fast_fix.service.interfaces.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InspectionReminderServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 12 * * ?") // Ежедневный запуск в 12:00
    public void sendInspectionReminders() {
        LocalDate now = LocalDate.now();
        LocalDate reminderDate = now.plusMonths(1);

        List<User> users = userRepository.findAll();
        for (User user : users) {
            CarDetails carDetails = user.getCarDetails();
            if (carDetails != null && carDetails.getLastMaintenanceDate() != null) {
                LocalDate inspectionDate = carDetails.getLastMaintenanceDate().plusMonths(11);
                if (inspectionDate.equals(reminderDate)) {
                    emailService.sendMaintenanceDateChangedEmail(user);
                }
            }
        }
    }
}
