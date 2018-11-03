package com.evgeniiavak.studyhibernate.model.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue
    private UUID id;

    @ElementCollection
    private List<String> names = new ArrayList<>();

    @ElementCollection
    private Set<Phone> phones = new HashSet<>();

    @ElementCollection
    @org.hibernate.annotations.GenericGenerator(name="uuid-generator",strategy="org.hibernate.id.UUIDGenerator")
    @org.hibernate.annotations.CollectionId(
            columns = @Column(name = "id"),
            type = @org.hibernate.annotations.Type(type = "pg-uuid"),
            generator = "uuid-generator")
    private List<Account> accounts = new ArrayList<>();

    @ElementCollection
    @OrderColumn      // <- ignored!
    private Collection<Address> addresses = new ArrayList<>();


    @ElementCollection
    @OrderColumn
    private List<Address> orderedAddresses = new ArrayList<>();

}

