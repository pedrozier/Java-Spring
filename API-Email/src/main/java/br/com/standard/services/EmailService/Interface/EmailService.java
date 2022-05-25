package br.com.standard.services.EmailService.Interface;

import br.com.standard.model.EmailModel;

public interface EmailService {

    EmailModel sendMail(EmailModel emailModel);

}
