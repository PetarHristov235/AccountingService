package com.accounting.demo.rest.delegate;

import com.accounting.demo.api.TransfersApiDelegate;
import com.accounting.demo.model.CreateTransferDTO;
import com.accounting.demo.model.TransferDTO;
import com.accounting.demo.service.TransferService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class TransfersApiDelegateImpl implements TransfersApiDelegate {

    TransferService transferService;

    @Override
    public ResponseEntity<TransferDTO> getTransferById(Long transferId) {
        return ResponseEntity.ok(transferService.getTransferById(transferId));
    }

    @Override
    public ResponseEntity<List<TransferDTO>> getTransfersByAccountId(Long accountId) {
        return ResponseEntity.ok(transferService.getTransfersByAccountId(accountId));
    }

    @Override
    public ResponseEntity<TransferDTO> createTransfer(CreateTransferDTO createTransferDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transferService.createTransfer(createTransferDTO));
    }
}