package com.example.FBJV24001114synrgy7josBinarFudch5.model.entity;

import javax.persistence.*;

import com.example.FBJV24001114synrgy7josBinarFudch5.model.entity.oauth.AuthUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "merchant")
public class Merchant {
    @Id
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID id;

    @Column(name = "merchant_name")
    private String name;

    @Column(name = "merchant_location")
    private String location;

    private Boolean open;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AuthUser authUser;


    @OneToMany(mappedBy = "merchant")
    private List<Product> products;
}
