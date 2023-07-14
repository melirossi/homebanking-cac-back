package com.ar.bankingmelisa.application.services;

import com.ar.bankingmelisa.api.dtos.TransferDto;
import com.ar.bankingmelisa.api.mappers.TransferMapper;
import com.ar.bankingmelisa.application.exceptions.InsufficientFundsException;
import com.ar.bankingmelisa.domain.exceptions.AccountNotFoundException;
import com.ar.bankingmelisa.domain.exceptions.TransferNotFoundException;
import com.ar.bankingmelisa.domain.models.Account;
import com.ar.bankingmelisa.domain.models.Transfer;
import com.ar.bankingmelisa.infrastructure.repositories.AccountRepository;
import com.ar.bankingmelisa.infrastructure.repositories.TransfersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferService {

    @Autowired
    private TransfersRepository repository;

    @Autowired
    private AccountRepository accountRepository;

    public TransferService(TransfersRepository repository){
        this.repository = repository;
    }

    /*
    Retrieve all transfers.
    @return List of TransferDto objects.
    */
    public List<TransferDto> getTransfers(){
        List<Transfer> transfers = repository.findAll();
        return transfers.stream()
                .map(TransferMapper::transferToDto)
                .collect(Collectors.toList());
    }

    /*
    Get a transfer by ID.
    @param id Transfer ID.
    @return TransferDto object.
    @throws TransferNotFoundException if transfer with the given ID is not found.
    */
    public TransferDto getTransferById(Long id){
        Transfer transfer = repository.findById(id).orElseThrow(() ->
                new TransferNotFoundException("Transfer not found with id: " + id));
        return TransferMapper.transferToDto(transfer);
    }

    /*
    Update a transfer.
    @param id Transfer ID.
    @param transferDto Updated TransferDto object.
    @return Updated TransferDto object.
    @throws TransferNotFoundException if transfer with the given ID is not found.
    */
    public TransferDto updateTransfer(Long id, TransferDto transferDto){
        Transfer transfer = repository.findById(id).orElseThrow(() ->
                new TransferNotFoundException("Transfer not found with id: " + id));
        Transfer updatedTransfer = TransferMapper.dtoToTransfer(transferDto);
        updatedTransfer.setId(transfer.getId());
        return TransferMapper.transferToDto(repository.save(updatedTransfer));
    }

    /*
    Delete a transfer by ID.
    @param id Transfer ID.
    @return Result message.
    */
    public String deleteTransfer(Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return "Se ha eliminado la transferencia";
        } else {
            return "No se ha eliminado la transferencia";
        }
    }

    /*
    Perform a transfer between accounts.
    @param dto TransferDto object containing transfer details.
    @return TransferDto object of the performed transfer.
    @throws AccountNotFoundException   if the origin or destination account is not found.
    @throws InsufficientFundsException if the origin account has insufficient funds.
    */
    @Transactional
    public TransferDto performTransfer(TransferDto dto) {
        // Check if the origin and destination accounts exist
        Account originAccount = accountRepository.findById(dto.getOrigin())
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + dto.getOrigin()));
        Account destinationAccount = accountRepository.findById(dto.getTarget())
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + dto.getTarget()));

        // Check if the origin account has sufficient funds
        if (originAccount.getBalance().compareTo(dto.getAmount()) < 0) {
            throw new InsufficientFundsException("Insufficient funds in the account with id: " + dto.getOrigin());
        }

        // Perform the transfer
        originAccount.setBalance(originAccount.getBalance().subtract(dto.getAmount()));
        destinationAccount.setBalance(destinationAccount.getBalance().add(dto.getAmount()));

        // Save the updated accounts
        accountRepository.save(originAccount);
        accountRepository.save(destinationAccount);

        // Create the transfer and save it to the database
        Transfer transfer = new Transfer();
        Date date = new Date();
        transfer.setDate(date);
        transfer.setOrigin(originAccount.getId());
        transfer.setTarget(destinationAccount.getId());
        transfer.setAmount(dto.getAmount());
        transfer = repository.save(transfer);

        // Return the DTO of the performed transfer
        return TransferMapper.transferToDto(transfer);
    }
}


