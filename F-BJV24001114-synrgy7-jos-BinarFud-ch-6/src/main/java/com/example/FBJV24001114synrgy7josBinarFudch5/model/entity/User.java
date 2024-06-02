package com.example.FBJV24001114synrgy7josBinarFudch5.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID id;

    private String username;

    @Column(name = "email_address")
    private String email;

    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}
