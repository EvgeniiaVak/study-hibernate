package com.evgeniiavak.studyhibernate.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractChildEntity {
    @Id
    @GeneratedValue(generator = "custom-uuid-generator")
    protected UUID id;
}
