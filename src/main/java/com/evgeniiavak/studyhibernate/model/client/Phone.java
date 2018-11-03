package com.evgeniiavak.studyhibernate.model.client;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Phone {
    private String number;
}
