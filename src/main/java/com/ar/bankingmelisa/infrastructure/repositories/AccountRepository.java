package com.ar.bankingmelisa.infrastructure.repositories;

import com.ar.bankingmelisa.domain.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// The @Repository annotation is used to indicate that this interface is a repository component.
// It is responsible for persisting and retrieving entities of the Account class.
public interface AccountRepository extends JpaRepository<Account, Long> {
    // This interface inherits various methods from the JpaRepository interface,
    // such as save(), findById(), findAll(), deleteById(), etc.
    // These methods provide basic CRUD operations for the Account entity.
}

