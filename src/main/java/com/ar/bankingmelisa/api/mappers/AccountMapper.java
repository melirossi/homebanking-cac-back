package com.ar.bankingmelisa.api.mappers;

import com.ar.bankingmelisa.api.dtos.AccountDto;
import com.ar.bankingmelisa.api.dtos.UserDto;
import com.ar.bankingmelisa.domain.models.Account;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountMapper {

    /*
    Maps an AccountDto object to an Account object by setting the balance.
    @param dto the AccountDto object to be mapped
    @return the mapped Account object
    */
    public Account dtoToAccount(AccountDto dto){
        Account account = new Account();
        account.setBalance(dto.getAmount());
        return account;
    }

    /*
    * Maps an Account object to an AccountDto object by setting the balance, owner and Id.
    * @param account the Account object to be mapped
    * @return the mapped AccountDto object
    */
    public AccountDto AccountToDto(Account account){
        AccountDto dto = new AccountDto();
        dto.setAmount(account.getBalance());
        if (account.getOwner()!=null){
            UserDto userDto=UserMapper.userMapToDto(account.getOwner());
            dto.setOwner(userDto);
        }
        dto.setId(account.getId());
        return dto;
    }
}
