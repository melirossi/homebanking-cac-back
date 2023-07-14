package com.ar.bankingmelisa.api.controllers;

import com.ar.bankingmelisa.api.dtos.TransferDto;
import com.ar.bankingmelisa.application.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TransferController {

    private TransferService service;

    @Autowired
    public TransferController(TransferService service) {
        this.service = service;
    }

    /*
    GET /transfers
    Retrieve all transfers.
    @return ResponseEntity<List<TransferDto>> - List of TransferDto objects as response body.
    */
    @GetMapping(value = "/transfers")
    public ResponseEntity<List<TransferDto>> getTransfers() {
        List<TransferDto> transfers = service.getTransfers();
        return ResponseEntity.status(HttpStatus.OK).body(transfers);
    }

    /*
    GET /transfers/{id}
    Retrieve a transfer by its ID.
    param id - ID of the transfer.
    @return ResponseEntity<TransferDto> - TransferDto object as response body.
    */
    @GetMapping(value = "/transfers/{id}")
    public ResponseEntity<TransferDto> getTransferById(@PathVariable Long id) {
        TransferDto transfer = service.getTransferById(id);
        return ResponseEntity.status(HttpStatus.OK).body(transfer);
    }

    /*
    POST /transfers
    Perform a transfer between accounts.
    @param dto - TransferDto object containing transfer information.
    @return ResponseEntity<TransferDto> - Created TransferDto object as response body.
    */
    @PostMapping(value = "/transfers")
    public ResponseEntity<TransferDto> performTransfer(@RequestBody TransferDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.performTransfer(dto));
    }

    /*
    PUT /transfers/{id}
    Update an existing transfer.
    @param id - ID of the transfer to be updated.
    @param transfer - Updated TransferDto object.
    @return ResponseEntity<TransferDto> - Updated TransferDto object as response body.
    */
    @PutMapping(value = "/transfers/{id}")
    public ResponseEntity<TransferDto> updateTransfer(@PathVariable Long id, @RequestBody TransferDto transfer) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateTransfer(id, transfer));
    }

    /*
    DELETE /transfers/{id}
    Delete a transfer by its ID.
    @param id - ID of the transfer to be deleted.
    @return ResponseEntity<String> - Response message indicating the result of the operation.
    */
    @DeleteMapping(value = "/transfers/{id}")
    public ResponseEntity<String> deleteTransfer(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteTransfer(id));
    }
}
