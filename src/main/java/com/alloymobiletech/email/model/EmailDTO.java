package com.alloymobiletech.email.model;

import lombok.Data;

@Data
public class EmailDTO {
    private String toMailAddress;
    private String subject;
    private String body;
}
