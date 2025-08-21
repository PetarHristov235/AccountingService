package com.accounting.demo.service;

import com.accounting.demo.entity.AccountEntity;
import com.accounting.demo.entity.TransferEntity;
import com.accounting.demo.mapper.TransferMapper;
import com.accounting.demo.model.CreateTransferDTO;
import com.accounting.demo.model.TransferDTO;
import com.accounting.demo.repository.AccountRepository;
import com.accounting.demo.repository.TransferRepository;
import com.accounting.demo.validator.TransferValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class TransferServiceImpl implements TransferService {
    TransferRepository transferRepository;
    TransferMapper transferMapper;
    TransferValidator transferValidator;
    AccountRepository accountRepository;

    @Override
    public TransferDTO getTransferById(Long transferId) {
        Optional<TransferEntity> optTransferEntity = transferRepository.findById(transferId);
        transferValidator.validateTransferExists(optTransferEntity);
        return transferMapper.toDto(optTransferEntity.get());
    }

    @Override
    public List<TransferDTO> getTransfersByAccountId(Long accountId) {
        List<TransferEntity> transferEntityList = transferRepository.findByAccountId(accountId);
        transferValidator.validateTransfersExists(transferEntityList);
        return transferMapper.toDtoList(transferEntityList);
    }

    @Override
    @Transactional
    public TransferDTO createTransfer(CreateTransferDTO transferDTO) {
        Optional<AccountEntity> optSourceAccount =
                accountRepository.findById(transferDTO.getAccountId());
        Optional<AccountEntity> optBeneficiaryAccount =
                accountRepository.findById(transferDTO.getBeneficiaryAccountId());

        transferValidator.validateCreateTransfer(transferDTO, optSourceAccount, optBeneficiaryAccount);
        AccountEntity accountEntity = optSourceAccount.get();
        AccountEntity beneficiaryAccountEntity = optBeneficiaryAccount.get();
        transferFunds(transferDTO.getAmount(), transferDTO.getType(), accountEntity, beneficiaryAccountEntity);

        TransferEntity transferEntity = transferMapper.toEntity(transferDTO, accountEntity, beneficiaryAccountEntity);
        transferEntity = transferRepository.save(transferEntity);
        return transferMapper.toDto(transferEntity);
    }

    private void transferFunds(BigDecimal amount, CreateTransferDTO.TypeEnum typeEnum,
                               AccountEntity account,
                               AccountEntity beneficiaryAccount) {

        BigDecimal sourceAmount;
        BigDecimal destinationAmount;
        switch (typeEnum) {
            case DEBIT -> {
                sourceAmount = amount.negate();
                destinationAmount = amount;
            }
            case CREDIT -> {
                sourceAmount = amount;
                destinationAmount = amount.negate();
            }
            default -> throw new IllegalArgumentException("Unsupported transfer type: " + typeEnum);
        }

        account.setAvailableAmount(account.getAvailableAmount().add(sourceAmount));
        beneficiaryAccount.setAvailableAmount(beneficiaryAccount.getAvailableAmount().add(destinationAmount));
    }
}


