package com.ar.bankingmelisa.api.dtos;

import lombok.*;
import java.util.List;

@Data
// The @Data annotation automatically generates getter and setter methods for the attributes, as well as other useful methods such as toString(), equals(), and hashCode().
public class UserDto {

    public UserDto(){}

    private Long id; // The ID of the user.
    private String username; // The username of the user.
    private String password; // The password of the user.
    private List<Long> idAccounts; // A list of account IDs associated with the user.
}

