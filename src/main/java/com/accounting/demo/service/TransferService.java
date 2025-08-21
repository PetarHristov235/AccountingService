package com.accounting.demo.service;

import com.accounting.demo.model.CreateTransferDTO;
import com.accounting.demo.model.TransferDTO;

import java.util.List;

public interface TransferService {

    TransferDTO getTransferById(Long transferId);

    List<TransferDTO> getTransfersByAccountId(Long accountId);

    TransferDTO createTransfer(CreateTransferDTO transferDTO);
    }
