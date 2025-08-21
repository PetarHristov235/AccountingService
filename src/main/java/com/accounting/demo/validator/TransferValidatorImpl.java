package com.accounting.demo.validator;

import com.accounting.demo.AccountingApplication;
import com.accounting.demo.entity.AccountEntity;
import com.accounting.demo.entity.TransferEntity;
import com.accounting.demo.enumerator.AccountStatusEnum;
import com.accounting.demo.exception.ApiException;
import com.accounting.demo.model.CreateTransferDTO;
import com.accounting.demo.model.UpdateAccountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.accounting.demo.enumerator.ApiExceptionEnum.*;

@Component
@RequiredArgsConstructor
public class TransferValidatorImpl implements TransferValidator {

    @Override
    public void validateTransferExists(Optional<TransferEntity> optTransferEntity) {
        if (optTransferEntity.isEmpty()) {
            throw new ApiException(TR_404.name(), TR_404.getErrorCode(), TR_404.getDescription());
        }
    }

    @Override
    public void validateTransfersExists(List<TransferEntity> transferEntityList) {
        if (transferEntityList.isEmpty()) {
            throw new ApiException(TR_405.name(), TR_405.getErrorCode(), TR_405.getDescription());
        }
    }

    @Override
    public void validateCreateTransfer(CreateTransferDTO transferDTO, Optional<AccountEntity> optSourceAccount, Optional<AccountEntity> optBeneficiaryAccount) {
        if (optSourceAccount.isEmpty()) {
            throw new ApiException(TR_406.name(), TR_406.getErrorCode(), TR_406.getDescription());
        }

        if (optBeneficiaryAccount.isEmpty()) {
            throw new ApiException(TR_407.name(), TR_407.getErrorCode(), TR_407.getDescription());
        }

        if (optSourceAccount.get().getStatus().equals(AccountStatusEnum.FROZEN)
                || optBeneficiaryAccount.get().getStatus().equals(AccountStatusEnum.FROZEN)) {
            throw new ApiException(TR_413.name(), TR_413.getErrorCode(), TR_413.getDescription());
        }

        if (transferDTO.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ApiException(TR_408.name(), TR_408.getErrorCode(), TR_408.getDescription());
        }

        if (transferDTO.getAccountId().equals(transferDTO.getBeneficiaryAccountId())) {
            throw new ApiException(TR_409.name(), TR_409.getErrorCode(), TR_409.getDescription());
        }

        if (!transferDTO.getType().equals(CreateTransferDTO.TypeEnum.CREDIT)
                && !transferDTO.getType().equals(CreateTransferDTO.TypeEnum.DEBIT)) {
            throw new ApiException(TR_412.name(), TR_412.getErrorCode(), TR_412.getDescription());
        }

        if (transferDTO.getType().equals(CreateTransferDTO.TypeEnum.DEBIT)
                && optSourceAccount.get().getAvailableAmount().subtract(transferDTO.getAmount()).compareTo(BigDecimal.ZERO) < 0) {
            throw new ApiException(TR_410.name(), TR_410.getErrorCode(), TR_410.getDescription());
        }

        if (transferDTO.getType().equals(CreateTransferDTO.TypeEnum.CREDIT)
                && optBeneficiaryAccount.get().getAvailableAmount().subtract(transferDTO.getAmount()).compareTo(BigDecimal.ZERO) < 0) {
            throw new ApiException(TR_411.name(), TR_411.getErrorCode(), TR_411.getDescription());
        }
    }
}
