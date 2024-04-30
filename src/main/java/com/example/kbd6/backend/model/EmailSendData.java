package com.example.kbd6.backend.model;

import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailSendData {

    private String from;
    private String to;
    private String subject;
    private String content;
    private boolean mailsend;
    private String purchaseid;
}
