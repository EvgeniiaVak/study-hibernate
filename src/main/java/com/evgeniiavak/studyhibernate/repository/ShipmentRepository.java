package com.evgeniiavak.studyhibernate.repository;

import com.evgeniiavak.studyhibernate.model.shipment.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShipmentRepository extends JpaRepository<Shipment, UUID> {
}
