package com.ar.bankingmelisa.application.services;

import com.ar.bankingmelisa.api.mappers.UserMapper;
import com.ar.bankingmelisa.domain.exceptions.AccountNotFoundException;
import com.ar.bankingmelisa.domain.models.Account;
import com.ar.bankingmelisa.domain.models.User;
import com.ar.bankingmelisa.api.dtos.UserDto;
import com.ar.bankingmelisa.infrastructure.repositories.AccountRepository;
import com.ar.bankingmelisa.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AccountRepository accountRepository;

    public UserService(UserRepository repository, AccountRepository accountRepository){
        this.repository = repository;
        this.accountRepository = accountRepository;
    }

    /*
    Get all users.
    @return List of UserDto objects.
     */
    public List<UserDto> getUsers(){
        List<User> users = repository.findAll();
        return users.stream()
                .map(UserMapper::userMapToDto)
                .toList();
    }

    /*
    Get a user by ID.
    @param id User ID.
    @return UserDto object.
    */
    public UserDto getUserById(Long id){
        return UserMapper.userMapToDto(repository.findById(id).get());
    }


    /*
    Create a new user.
    @param user UserDto object containing user details.
    @return Created UserDto object.
    */
    public UserDto createUser(UserDto user){
        return UserMapper.userMapToDto(repository.save(UserMapper.dtoToUser(user)));
    }

    /*
    Update a user.
    @param id   User ID.
    @param user Updated UserDto object.
    @return Updated UserDto object.
    @throws AccountNotFoundException if user with the given ID is not found.
    */
    public UserDto update(Long id, UserDto user){
        Optional<User> userCreated = repository.findById(id);

        if (userCreated.isPresent()){
            User entity = userCreated.get();

            User accountUpdated = UserMapper.dtoToUser(user);
            accountUpdated.setAccounts(entity.getAccounts());

            if (user.getIdAccounts() != null) {
                List<Account> accountList = accountRepository.findAllById(user.getIdAccounts());
                List<Account> accountListFilter = accountList.stream().filter(e -> !entity.getAccounts().contains(e)).toList();
                accountUpdated.getAccounts().addAll(accountListFilter);
                accountUpdated.setAccounts(accountList);
            }

            accountUpdated.setId(entity.getId());

            User saved = repository.save(accountUpdated);

            return UserMapper.userMapToDto(saved);
        } else {
            throw new AccountNotFoundException("User not found with id: " + id);
        }
    }

    /*
    Delete a user by ID.
    @param id User ID.
    @return Result message.
    */
    public String delete(Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return "Se ha eliminado la cuenta";
        } else {
            return "No se ha eliminado la cuenta";
        }
    }

}



