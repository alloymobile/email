package com.alloymobiletech.email.resource;

import com.alloymobiletech.email.integration.EmailService;
import com.alloymobiletech.email.model.EmailDTO;
import com.alloymobiletech.email.model.ResponseDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Send email APIs", description = "The API is used to send email to any valid email address")
public class EmailResource {

    private final EmailService emailService;

    public EmailResource(EmailService emailService) {
        this.emailService = emailService;
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping(value = "/api/v1/email",consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseDTO> sendSms(@RequestBody EmailDTO emailDTO){
        return emailService.sendEmail(emailDTO);
    }
}
