package com.evgeniiavak.studyhibernate.model.shipment;


import com.evgeniiavak.studyhibernate.model.AbstractChildEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
public class Package extends AbstractChildEntity {

    @NotNull
    private String code;

    public Package(String code) {
        this.code = code;
    }
}
