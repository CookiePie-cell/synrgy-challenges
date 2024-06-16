package com.challenge7.auth.repository;


import com.challenge7.auth.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findOneByClientId(String clientId);

}
