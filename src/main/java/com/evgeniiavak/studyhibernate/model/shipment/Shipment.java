package com.evgeniiavak.studyhibernate.model.shipment;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Shipment {

    @Id
    private UUID id = UUID.randomUUID();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipment_id", nullable = false)
    private List<Package> packages = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @org.hibernate.annotations.CollectionId(
            columns = @Column(name = "id"),
            type = @org.hibernate.annotations.Type(type = "pg-uuid"),
            generator = "custom-uuid-generator")
    @GenericGenerator(name = "custom-uuid-generator",
            strategy = "com.evgeniiavak.studyhibernate.extentions.UuidGenerator")
    private List<Item> items = new ArrayList<>();
}
