package com.ar.bankingmelisa.infrastructure.repositories;

import com.ar.bankingmelisa.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// The @Repository annotation is used to indicate that this interface is a repository component.
// It is responsible for persisting and retrieving entities of the Account class.
public interface UserRepository extends JpaRepository<User, Long> {
    // This interface inherits various methods from the JpaRepository interface,
    // such as save(), findById(), findAll(), deleteById(), etc.
    // These methods provide basic CRUD operations for the User entity.
}

