package com.example.FBJV24001114synrgy7josBinarFudch5.repository.oauth;

import com.example.FBJV24001114synrgy7josBinarFudch5.model.entity.oauth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findOneByName(String name);

    List<Role> findByNameIn(String[] names);
}
