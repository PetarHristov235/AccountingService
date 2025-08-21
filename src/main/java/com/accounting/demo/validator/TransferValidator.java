package com.accounting.demo.validator;

import com.accounting.demo.entity.AccountEntity;
import com.accounting.demo.entity.TransferEntity;
import com.accounting.demo.model.CreateTransferDTO;

import java.util.List;
import java.util.Optional;

public interface TransferValidator {
    void validateTransferExists(Optional<TransferEntity> optTransferEntity);
    void validateTransfersExists(List<TransferEntity> transferEntityList);

    void validateCreateTransfer(CreateTransferDTO transferDTO, Optional<AccountEntity> optSourceAccount, Optional<AccountEntity> optBeneficiaryAccount);
}
