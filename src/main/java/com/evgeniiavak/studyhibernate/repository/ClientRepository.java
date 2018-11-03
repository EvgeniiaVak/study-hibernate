package com.evgeniiavak.studyhibernate.repository;

import com.evgeniiavak.studyhibernate.model.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
}
