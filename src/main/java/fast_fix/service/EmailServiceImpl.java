package fast_fix.service;

import fast_fix.domain.entity.User;
import fast_fix.service.interfaces.ConfirmationService;
import fast_fix.service.interfaces.EmailService;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender sender;
    private Configuration mailConfiguration;
    private ConfirmationService confirmationService;

    public EmailServiceImpl(JavaMailSender sender, Configuration mailConfiguration, ConfirmationService confirmationService) {
        this.sender = sender;
        this.mailConfiguration = mailConfiguration;
        this.confirmationService = confirmationService;

        mailConfiguration.setDefaultEncoding("UTF-8");
        mailConfiguration.setTemplateLoader(new ClassTemplateLoader(EmailServiceImpl.class, "/mail/"));
    }

    @Override
    public void sendConfirmationEmail(User user, String confirmationCode) {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        String text = generateMessageText(user, confirmationCode);

        try {
            helper.setFrom("fastfix2024project@gmail.com");
            helper.setTo(user.getEmail());
            helper.setSubject("Registration");
            helper.setText(text, true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        sender.send(message);
    }

    private String generateMessageText(User user, String confirmationCode) {
        try {
            Template template = mailConfiguration.getTemplate("confirmation_registration_mail.ftlh");

            Map<String, Object> model = new HashMap<>();
            model.put("name", user.getUsername());
            model.put("link", "http://localhost:8080/api/confirm?code=" + confirmationCode);

            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendInsuranceChangedInfoEmail(User user) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,"UTF-8");
        String text = generateInsuranceChangedMessageText(user);

        try {
            helper.setFrom("fastfix2024project@gmail.com");
            helper.setTo(user.getEmail());
            helper.setSubject("Insurance Changed");
            helper.setText(text, true);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        sender.send(message);
    }
    private String generateInsuranceChangedMessageText(User user) {
        try {
            Template template = mailConfiguration
                    .getTemplate("info_insurance_changed.ftlh");

            Map<String, Object> model = new HashMap<>();
            model.put("name", user.getUsername());
            model.put("insurance_company", user.getCarDetails().getInsuranceCompany());

            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendFuelParamInfoEmail(User user) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,"UTF-8");
        String text = generateFuelParamMessageText(user);

        try {
            helper.setFrom("fastfix2024project@gmail.com");
            helper.setTo(user.getEmail());
            helper.setSubject("Fuel Param");
            helper.setText(text, true);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        sender.send(message);
    }
    private String generateFuelParamMessageText(User user) {
        try {
            Template template = mailConfiguration
                    .getTemplate("info_new_fuel_param.ftlh");

            Map<String, Object> model = new HashMap<>();
            model.put("name", user.getUsername());

            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void sendMaintenanceDateChangedEmail(User user) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,"UTF-8");
        String text = generateMaintenanceDateMessageText(user);

        try {
            helper.setFrom("fastfix2024project@gmail.com");
            helper.setTo(user.getEmail());
            helper.setSubject("Technical Inspection");
            helper.setText(text, true);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        sender.send(message);
    }
    private String generateMaintenanceDateMessageText(User user) {
        try {
            Template template = mailConfiguration
                    .getTemplate("info_maintenance_date_changed.ftlh");

            Map<String, Object> model = new HashMap<>();
            model.put("name", user.getUsername());

            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendDeleteAccountInfoEmail(User user) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,"UTF-8");
        String text = generateDeleteAccountMessageText(user);

        try {
            helper.setFrom("fastfix2024project@gmail.com");
            helper.setTo(user.getEmail());
            helper.setSubject("Account Deletion");
            helper.setText(text, true);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        sender.send(message);
    }
    private String generateDeleteAccountMessageText(User user) {
        try {
            Template template = mailConfiguration
                    .getTemplate("info_delete_account.ftlh");

            Map<String, Object> model = new HashMap<>();
            model.put("name", user.getUsername());

            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMaintenanceReminderEmail(User user) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        String text = generateMaintenanceReminderMessageText(user);

        try {
            helper.setFrom("fastfix2024project@gmail.com");
            helper.setTo(user.getEmail());
            helper.setSubject("Technical Inspection Reminder");
            helper.setText(text, true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        sender.send(message);
    }

    private String generateMaintenanceReminderMessageText(User user) {
        try {
            Template template = mailConfiguration.getTemplate("warning_maintenance_date_due.ftlh");

            Map<String, Object> model = new HashMap<>();
            model.put("name", user.getUsername());
            model.put("date", user.getCarDetails().getLastMaintenanceDate().toString());

            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}