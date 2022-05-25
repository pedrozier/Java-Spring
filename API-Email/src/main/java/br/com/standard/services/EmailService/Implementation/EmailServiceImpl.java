package br.com.standard.services.EmailService.Implementation;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.standard.enums.StatusEmail;
import br.com.standard.model.EmailModel;
import br.com.standard.repository.EmailRepository;
import br.com.standard.services.EmailService.Interface.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailRepository repository;

    @Autowired
    private JavaMailSender emailSender;

    @Override // GMAIL //
    public EmailModel sendMail(EmailModel emailModel) {
        emailModel.setSendDataEmail(LocalDateTime.now());
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SENDED);
        } catch (Exception e) {
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return repository.save(emailModel);
        }

    }

}
