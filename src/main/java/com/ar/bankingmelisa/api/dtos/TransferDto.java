package com.ar.bankingmelisa.api.dtos;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
// The @Data annotation automatically generates getter and setter methods for the attributes, as well as other useful methods such as toString(), equals(), and hashCode().
public class TransferDto {

    private Long id; // The ID of the transfer.
    private Long origin; // The ID of the origin account.
    private Long target; // The ID of the target account.
    private Date date; // The date of the transfer.
    private BigDecimal amount; // The amount of money transferred.
}

