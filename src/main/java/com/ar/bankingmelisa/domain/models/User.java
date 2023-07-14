package com.ar.bankingmelisa.domain.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
// The @Entity annotation marks this class as an entity that can be persisted to a relational database.
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    // The @Id annotation specifies that the 'id' field is the primary key of the entity.
    // The @GeneratedValue annotation indicates that the value of the 'id' field will be automatically generated by the database.
    private Long id;

    private String username;
    // The 'username' field stores the username of the user.

    private String password;
    // The 'password' field stores the password of the user.

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    // The @OneToMany annotation establishes a one-to-many relationship between User and Account entities.
    // The 'mappedBy' attribute specifies that the 'owner' field in the Account entity is the inverse side of the relationship.
    // The cascade attribute indicates that all cascading operations (such as persist, merge, remove) should be applied to the associated Account entities.
    // The orphanRemoval attribute indicates that when an Account is removed from the accounts list, it should be removed from the database as well.
    private List<Account> accounts;
    // The 'accounts' field represents the list of accounts associated with the user.
}
