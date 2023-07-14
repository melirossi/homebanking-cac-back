package com.ar.bankingmelisa.api.mappers;

import com.ar.bankingmelisa.api.dtos.TransferDto;
import com.ar.bankingmelisa.domain.models.Transfer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TransferMapper {

    /*
    Maps a TransferDto object to a Transfer object by setting the date, origin, target and amount.
    @param dto the TransferDto object to be mapped
    @return the mapped Transfer object
    */
    public Transfer dtoToTransfer(TransferDto dto){
        Transfer transfer = new Transfer();
        transfer.setDate(dto.getDate());
        transfer.setOrigin(dto.getOrigin());
        transfer.setTarget(dto.getTarget());
        transfer.setAmount(dto.getAmount());
        return transfer;
    }

    /*
    Maps a Transfer object to a TransferDto object by setting the date, origin, target, amount and Id.
    @param transfer the Transfer object to be mapped
    @return the mapped TransferDto object
    */
    public TransferDto transferToDto(Transfer transfer){
        TransferDto dto = new TransferDto();
        dto.setDate(transfer.getDate());
        dto.setOrigin(transfer.getOrigin());
        dto.setTarget(transfer.getTarget());
        dto.setAmount(transfer.getAmount());
        dto.setId(transfer.getId());
        return dto;
    }

}

