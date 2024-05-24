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
    public void sendConfirmationEmail(User user) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,"UTF-8");
        String text = generateMessageText(user);

        try {
            helper.setFrom("mail.test.er.anny@gmail.com");
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
}