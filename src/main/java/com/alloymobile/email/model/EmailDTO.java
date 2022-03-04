package com.alloymobile.email.model;

import lombok.Data;

@Data
public class EmailDTO {
    private String fromMailAddress;
    private String toMailAddress;
    private String subject;
    private String body;
}
