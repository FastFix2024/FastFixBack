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

import freemarker.template.Configuration;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender sender;
    private Configuration mailConfiguration;
    private ConfirmationService confirmationService;

    public EmailServiceImpl(JavaMailSender sender, Configuration mailConfiguration, ConfirmationService confirmationService){
        this.sender = sender;
        this.mailConfiguration = mailConfiguration;
        this.confirmationService = confirmationService;

        mailConfiguration.setDefaultEncoding("UTF-8");
        mailConfiguration.setTemplateLoader(
                new ClassTemplateLoader(EmailServiceImpl.class, "/mail/"));
    }

    @Override
    public void sendRegistrationConfirmEmail(User user) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,"UTF-8");
        String text = generateMessageText(user);

        try {
            helper.setFrom("fastfix2024project@gmail.com");
            helper.setTo(user.getEmail());
            helper.setSubject("Registration");
            helper.setText(text, true);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        sender.send(message);
    }
    private String generateMessageText(User user) {
        try {
            Template template = mailConfiguration
                    .getTemplate("confirm_registration_mail.ftlh");
            String code = confirmationService.generateConfirmationCode(user);

            Map<String, Object> model = new HashMap<>();
            model.put("name", user.getUsername());
            model.put("link", "http://localhost:8080/register?code=" + code);

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
            String code = confirmationService.generateConfirmationCode(user);

            Map<String, Object> model = new HashMap<>();
            model.put("name", user.getUsername());
            model.put("link", "http://localhost:8080/register?code=" + code);

            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void sendNewEmailInfoEmail(User user) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,"UTF-8");
        String text = generateNewEmailMessageText(user);

        try {
            helper.setFrom("fastfix2024project@gmail.com");
            helper.setTo(user.getEmail());
            helper.setSubject("New Email");
            helper.setText(text, true);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        sender.send(message);
    }
    private String generateNewEmailMessageText(User user) {
        try {
            Template template = mailConfiguration
                    .getTemplate("info_new_email.ftlh");
            String code = confirmationService.generateConfirmationCode(user);

            Map<String, Object> model = new HashMap<>();
            model.put("name", user.getUsername());
            model.put("link", "http://localhost:8080/register?code=" + code);

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
            String code = confirmationService.generateConfirmationCode(user);

            Map<String, Object> model = new HashMap<>();
            model.put("name", user.getUsername());
            model.put("link", "http://localhost:8080/register?code=" + code);

            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void sendTechInspectWarnEmail(User user) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,"UTF-8");
        String text = generateTechInspectMessageText(user);

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
    private String generateTechInspectMessageText(User user) {
        try {
            Template template = mailConfiguration
                    .getTemplate("warning_technical_inspection_date_due.ftlh");
            String code = confirmationService.generateConfirmationCode(user);

            Map<String, Object> model = new HashMap<>();
            model.put("name", user.getUsername());
            model.put("link", "http://localhost:8080/register?code=" + code);

            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void sendChangePasswordRequestWarnEmail(User user) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,"UTF-8");
        String text = generateChangePasswordRequestMessageText(user);

        try {
            helper.setFrom("fastfix2024project@gmail.com");
            helper.setTo(user.getEmail());
            helper.setSubject("New Password");
            helper.setText(text, true);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        sender.send(message);
    }
    private String generateChangePasswordRequestMessageText(User user) {
        try {
            Template template = mailConfiguration
                    .getTemplate("warning_change-password-request.ftlh");
            String code = confirmationService.generateConfirmationCode(user);

            Map<String, Object> model = new HashMap<>();
            model.put("name", user.getUsername());
            model.put("link", "http://localhost:8080/register?code=" + code);

            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void sendPasswordChangedInfoEmail(User user) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,"UTF-8");
        String text = generatePasswordChangedMessageText(user);

        try {
            helper.setFrom("fastfix2024project@gmail.com");
            helper.setTo(user.getEmail());
            helper.setSubject("New Password");
            helper.setText(text, true);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        sender.send(message);
    }
    private String generatePasswordChangedMessageText(User user) {
        try {
            Template template = mailConfiguration
                    .getTemplate("info_password_changed.ftlh");
            String code = confirmationService.generateConfirmationCode(user);

            Map<String, Object> model = new HashMap<>();
            model.put("name", user.getUsername());
            model.put("link", "http://localhost:8080/register?code=" + code);

            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}