package com.example.FBJV24001114synrgy7josBinarFudch5.repository.oauth;


import com.example.FBJV24001114synrgy7josBinarFudch5.model.entity.oauth.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findOneByClientId(String clientId);

}
