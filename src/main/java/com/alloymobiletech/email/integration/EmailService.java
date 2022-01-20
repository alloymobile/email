package com.alloymobiletech.email.integration;


import com.alloymobiletech.email.config.EmailProperties;
import com.alloymobiletech.email.exception.InternalServerException;
import com.alloymobiletech.email.model.EmailDTO;
import com.alloymobiletech.email.model.ResponseDTO;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    private final EmailProperties properties;
    
    private final SendGrid sendGrid;

    public EmailService(EmailProperties properties, SendGrid sendGrid) {
        this.properties = properties;
        this.sendGrid = sendGrid;
    }


    public ResponseEntity<ResponseDTO> sendEmail(EmailDTO emailDTO){
        String toEmail = emailDTO.getToMailAddress();
        String subject = emailDTO.getSubject();
        String body = emailDTO.getBody();

        Content content = new Content("text/html", body);

        Email from = new Email(properties.getFromEmail());

        Email to = new Email(toEmail);

        Mail mail = new Mail(from, subject, to, content);

        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            this.sendGrid.api(request);
        } catch (IOException ex) {
           throw new InternalServerException("Can not send email try again");
        }

        ResponseDTO messageDTO = new ResponseDTO();
        messageDTO.setStatus(HttpStatus.ACCEPTED.value());
        messageDTO.setMessage("Mail Sent successfully");
        return new ResponseEntity<>(messageDTO,HttpStatus.OK);
    }
}
