package com.ar.bankingmelisa.api.controllers;

import com.ar.bankingmelisa.api.dtos.AccountDto;
import com.ar.bankingmelisa.application.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {

    private AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    /*
    GET /accounts
    Retrieve all accounts.
    @return ResponseEntity<List<AccountDto>> - List of AccountDto objects as response body.
    */
    @GetMapping(value = "/accounts")
    public ResponseEntity<List<AccountDto>> getAccounts() {
        List<AccountDto> accounts = service.getAccounts();
        return ResponseEntity.status(HttpStatus.OK).body(accounts);
    }

    /*
    GET /accounts/{id}
    Retrieve an account by its ID.
    @param id - ID of the account.
    @return ResponseEntity<AccountDto> - AccountDto object as response body.
    */
    @GetMapping(value = "/accounts/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        AccountDto account = service.getAccountById(id);
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    /*
    POST /accounts
    Create a new account.
    @param dto - AccountDto object containing account information.
    @return ResponseEntity<AccountDto> - Created AccountDto object as response body.
    */
    @PostMapping(value = "/accounts")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createAccount(dto));
    }

    /*
    PUT /accounts/{id}
    Update an existing account.
    @param id - ID of the account to be updated.
    @param account - Updated AccountDto object.
    @return ResponseEntity<AccountDto> - Updated AccountDto object as response body.
    */
    @PutMapping(value = "/accounts/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable Long id, @RequestBody AccountDto account) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateAccount(id, account));
    }

    /*
    DELETE /accounts/{id}
    Delete an account by its ID.
    @param id - ID of the account to be deleted.
    @return ResponseEntity<String> - Response message indicating the result of the operation.
    */
    @DeleteMapping(value = "/accounts/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteAccount(id));
    }
}


