package com.example.FBJV24001114synrgy7josBinarFudch5.repository;

import com.example.FBJV24001114synrgy7josBinarFudch5.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Procedure(procedureName = "create_user")
    String saveUser(
            @Param("p_username") String username,
            @Param("p_email") String email,
            @Param("p_password") String password

    );

    @Procedure(procedureName = "update_user")
    String updateUser(
            @Param("p_id") UUID id,
            @Param("p_username") String username,
            @Param("p_email") String email,
            @Param("p_password") String password
    );

    @Procedure(procedureName = "delete_user")
    void deleteUser(
            @Param("p_id") UUID id
    );
}
