package com.evgeniiavak.studyhibernate.model.shipment;

import com.evgeniiavak.studyhibernate.model.AbstractChildEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item extends AbstractChildEntity {
    @NotNull
    private String name;
}
