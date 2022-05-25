package br.com.standard.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.standard.dto.EmailDto;
import br.com.standard.model.EmailModel;
import br.com.standard.services.EmailService.Interface.EmailService;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/send-mail")
    public ResponseEntity<EmailModel> sendEmail(@RequestBody @Valid EmailDto emailDto) {
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDto, emailModel);
        emailService.sendMail(emailModel);
        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
    }

}
