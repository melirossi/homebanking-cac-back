package com.ar.bankingmelisa.api.dtos;

import lombok.Data;
import java.math.BigDecimal;

@Data
// @Data generates automatically getter and setter methods for the attributes, as well as other useful methods such as toString(), equals(), and hashCode().
public class AccountDto {
    private Long id; // The ID of the account.
    private BigDecimal amount; // The amount/balance of the account.
    private UserDto owner; // The DTO representation of the owner/user of the account (`UserDto`).
}
