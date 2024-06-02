package com.example.FBJV24001114synrgy7josBinarFudch5.service;


import com.example.FBJV24001114synrgy7josBinarFudch5.model.entity.User;
import com.example.FBJV24001114synrgy7josBinarFudch5.model.entity.oauth.AuthUser;
import com.example.FBJV24001114synrgy7josBinarFudch5.repository.oauth.UserAuthRepository;
import com.example.FBJV24001114synrgy7josBinarFudch5.utils.EmailTemplate;
import com.example.FBJV24001114synrgy7josBinarFudch5.utils.SimpleStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.mail.internet.MimeMessage;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings({"WeakerAccess", "ConstantConditions"})
@Component("emailSender")
public class EmailSender {
    private final static Logger logger = LoggerFactory.getLogger(EmailSender.class);

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Value("${spring.mail.sender.name:}")
    private String senderName;

    @Value("${spring.mail.sender.mail:}")
    private String senderEmail;

    @Qualifier("taskExecutor")
    @Autowired
    private TaskExecutor taskExecutor;

    @Value("${expired.token.password.minute:}")//FILE_SHOW_RUL
    private int expiredToken;

    @Autowired
    private UserAuthRepository userAuthRepository;

    public boolean send(String email, String subject, String message) {
        return send(null, email, subject, message);
    }

    public boolean send(String from, String email, String subject, String message) {
        MimeMessage mime = mailSender.createMimeMessage();
        if (StringUtils.isEmpty(from)) {
            from = senderEmail;
        }
        if (StringUtils.isEmpty(from)) {
            from = "admin@mail.com";
        }
        boolean success = false;
        try {
            logger.info("Sending email to: "+email);
            logger.info("Sending email from: "+from);
            logger.info("Sending email with subject: "+subject);

            MimeMessageHelper helper = new MimeMessageHelper(mime, true);
            helper.setFrom(from,senderName);
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(message, true);
            mailSender.send(mime);
            success = true;
        } catch (Exception e) {
            logger.error("error: "+e.getMessage());
        }

        return success;
    }

    public void sendEmailRegister(AuthUser user) {
        String message = "Thanks, please check your email for activation.";

        String template = EmailTemplate.getRegisterTemplate();
        if (StringUtils.isEmpty(user.getOtp())) {
            AuthUser search;
            String otp;
            do {
                otp = SimpleStringUtils.randomString(6, true);
                search = userAuthRepository.findOneByOTP(otp);
            } while (search != null);
            Date dateNow = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateNow);
            calendar.add(Calendar.MINUTE, expiredToken);
            Date expirationDate = calendar.getTime();

            user.setOtpExpiredDate(expirationDate);
            user.setOtpExpiredDate(expirationDate);
            template = template.replaceAll("\\{\\{USERNAME}}", (user.getFullname() == null ? user.getUsername() : user.getFullname()));
            template = template.replaceAll("\\{\\{VERIFY_TOKEN}}",  otp);
            userAuthRepository.save(user);
        } else {
            template = template.replaceAll("\\{\\{USERNAME}}", (user.getFullname() == null ? user.getUsername() : user.getFullname()));
            template = template.replaceAll("\\{\\{VERIFY_TOKEN}}",  user.getOtp());
        }
    }

    public void sendAsync(final String to, final String subject, final String message) {
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {

                send(to, subject, message);
            }
        });
    }

}
