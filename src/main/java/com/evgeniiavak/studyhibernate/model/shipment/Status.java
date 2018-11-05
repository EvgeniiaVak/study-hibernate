package com.evgeniiavak.studyhibernate.model.shipment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Status {

    @Id
    @GeneratedValue
    private UUID id;

    @GeneratedValue
    @org.hibernate.annotations.Generated(GenerationTime.INSERT)
    private OffsetDateTime created;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.hibernate.annotations.Generated(GenerationTime.ALWAYS)
    private OffsetDateTime updated;

    @Enumerated(value = EnumType.STRING)
    private Name name;

    public Status(Name name) {
        this.name = name;
    }

    public enum Name {
        CREATED, IN_PROGRESS, COMPLETED;
    }
}
