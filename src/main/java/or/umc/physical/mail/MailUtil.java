package or.umc.physical.mail;



import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;


@Service
@RequiredArgsConstructor
public class MailUtil {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String configEmail;

    public void sendEmail() throws MessagingException {

        MimeMessage emailForm = createEmailForm();

        mailSender.send(emailForm);
    }


    private String setContext(String code) {
        Context context = new Context();
        TemplateEngine templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

        context.setVariable("code", code);


        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(false);

        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine.process("wakeup", context);
    }


    private MimeMessage createEmailForm() throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, "gdbs1107@naver.com");
        message.setSubject("주연아 일어나라 X100 🔔");
        message.setFrom(configEmail);

        // HTML 본문 설정
        String htmlContent = setContext("5"); // 5회 알람 예시
        message.setContent(htmlContent, "text/html; charset=utf-8");

        return message;
    }
}

