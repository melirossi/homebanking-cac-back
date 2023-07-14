package com.ar.bankingmelisa.application.services;

import com.ar.bankingmelisa.api.dtos.AccountDto;
import com.ar.bankingmelisa.api.mappers.AccountMapper;
import com.ar.bankingmelisa.domain.exceptions.AccountNotFoundException;
import com.ar.bankingmelisa.domain.models.Account;
import com.ar.bankingmelisa.domain.models.User;
import com.ar.bankingmelisa.infrastructure.repositories.AccountRepository;
import com.ar.bankingmelisa.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository repository;
    @Autowired
    private UserRepository userRepository;

    public AccountService(AccountRepository repository, UserRepository userRepository){
        this.repository = repository;
        this.userRepository = userRepository;
    }

    /*
    Retrieve all accounts.
    @return List of AccountDto objects.
    */
    @Transactional
    public List<AccountDto> getAccounts(){
        List<Account> accounts = repository.findAll();
        return accounts.stream()
                .map(AccountMapper::AccountToDto)
                .toList();
    }

    /*
    Create an account.
    @param account AccountDto object containing account details.
    @return Created AccountDto object.
    */
    @Transactional
    public AccountDto createAccount(AccountDto account){
        Optional<User> user = userRepository.findById(account.getOwner().getId());
        Account accountModel = AccountMapper.dtoToAccount(account);
        accountModel.setOwner(user.get());
        accountModel = repository.save(accountModel);
        AccountDto dto = AccountMapper.AccountToDto(accountModel);
        return dto;
    }

    /*
    Get an account by ID.
    @param id Account ID.
    @return AccountDto object.
     */
    @Transactional
    public AccountDto getAccountById(Long id) {
        AccountDto account = AccountMapper.AccountToDto(repository.findById(id).get());
        return account;
    }

    /*
    Update an account.
    @param id Account ID.
    @param account Updated AccountDto object.
    @return Updated AccountDto object.
    @throws AccountNotFoundException if account with the given ID is not found.
    */
    @Transactional
    public AccountDto updateAccount(Long id, AccountDto account){
        Optional<Account> accountCreated = repository.findById(id);
        if (accountCreated.isPresent()){
            Account entity = accountCreated.get();
            if (account.getAmount()!=null){
                entity.setBalance(account.getAmount());
            }
            if (account.getOwner()!=null){
                User user=userRepository.getReferenceById(account.getOwner().getId());
                if (user!=null){
                    entity.setOwner(user);
                }
            }
            Account saved = repository.save(entity);
            return AccountMapper.AccountToDto(saved);
        } else {
            throw new AccountNotFoundException("Account not found with id: " + id);
        }
    }

    /*
    Delete an account by ID.
    @param id Account ID.
    @return Result message.
    */
    @Transactional
    public String deleteAccount(Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return "Se ha eliminado la cuenta";
        } else {
            return "No se ha eliminado la cuenta";
        }
    }

    /*
    Withdraw money from an account.
    @param amount Amount to be withdrawn.
    @param idOrigin ID of the account.
    @return Remaining balance in the account.
    */
    public BigDecimal withdraw(BigDecimal amount, Long idOrigin){
        Account account = repository.findById(idOrigin).orElse(null);
        if (account.getBalance().subtract(amount).intValue() > 0){
            account.setBalance(account.getBalance().subtract(amount));
            repository.save(account);
        }
        return account.getBalance().subtract(amount);
    }

    /*
    Add money to an account.
    @param amount Amount to be added.
    @param idOrigin ID of the account.
    @return Added amount.
    */
    public BigDecimal addAmountToAccount(BigDecimal amount, Long idOrigin){
        Account account = repository.findById(idOrigin).orElse(null);
        account.setBalance(account.getBalance().add(amount));
        repository.save(account);
        return amount;
    }
}


