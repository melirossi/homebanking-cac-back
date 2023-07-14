package com.ar.bankingmelisa.api.mappers;

import com.ar.bankingmelisa.domain.models.Account;
import com.ar.bankingmelisa.domain.models.User;
import com.ar.bankingmelisa.api.dtos.UserDto;
import lombok.experimental.UtilityClass;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
// @UtilityClass generates utility methods for the class, such as making all methods statics and private constructor.
public class UserMapper {

    /*
    Maps a UserDto object to a User object by setting the username and password.
    @param dto the UserDto object to be mapped
    @return the mapped User object
    */
    public User dtoToUser(UserDto dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }

    /*
    Maps a User object to a UserDto object by setting the username, password, account IDs and user ID.
    It iterates over the accounts list and extracts the IDs of the associated accounts.
    @param user the User object to be mapped
    @return the mapped UserDto object
    */
    public UserDto userMapToDto(User user){
        UserDto dto = new UserDto();
        List<Long> accountsId = new ArrayList<>();

        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());

        if (user.getAccounts() != null) {
            for (Account a : user.getAccounts()) {
                Long id = a.getId();
                accountsId.add(id);
            }
        }

        dto.setIdAccounts(accountsId);
        dto.setId(user.getId());
        return dto;
    }

}
