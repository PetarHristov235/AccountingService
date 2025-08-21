package com.accounting.demo.validator;

import com.accounting.demo.entity.AccountEntity;
import com.accounting.demo.enumerator.AccountStatusEnum;
import com.accounting.demo.exception.ApiException;
import com.accounting.demo.model.CreateAccountDTO;
import com.accounting.demo.model.UpdateAccountDTO;
import com.accounting.demo.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.accounting.demo.enumerator.ApiExceptionEnum.*;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class AccountValidatorImpl implements AccountValidator {

    private final AccountRepository accountRepository;

    @Override
    public void validateAccountExists(Optional<AccountEntity> optAccountEntity) {
        if (optAccountEntity.isEmpty()) {
            throw new ApiException(ACC_404.name(), ACC_404.getErrorCode(),
                    ACC_404.getDescription());
        }
    }

    @Override
    public void validateFreezeAccount(Optional<AccountEntity> optAccountEntity) {
        validateAccountExists(optAccountEntity);
        AccountEntity accountEntity = optAccountEntity.get();
        if (accountEntity.getStatus().equals(AccountStatusEnum.FROZEN)) {
            throw new ApiException(ACC_32.name(), ACC_32.getErrorCode(), ACC_32.getDescription());
        }
    }

    @Override
    public void validateUnfreezeAccount(Optional<AccountEntity> optAccountEntity) {
        validateAccountExists(optAccountEntity);
        AccountEntity accountEntity = optAccountEntity.get();
        if (accountEntity.getStatus().equals(AccountStatusEnum.ACTIVE)) {
            throw new ApiException(ACC_33.name(), ACC_33.getErrorCode(), ACC_33.getDescription());
        }
    }

    @Override
    public void validateCreateAccount(CreateAccountDTO createAccountDTO) {
        validateAccountDoesNotExists(createAccountDTO.getName(), createAccountDTO.getIban());
    }

    @Override
    public void validateUpdateAccount(UpdateAccountDTO updateAccountDTO) {
        validateAccountDoesNotExists(updateAccountDTO.getName(), updateAccountDTO.getIban());
    }

    private void validateAccountDoesNotExists(String name, String iban) {
        boolean accountExists = accountRepository.existsByNameOrIban(name,
                iban);
        if (accountExists) {
            throw new ApiException(ACC_31.name(), ACC_31.getErrorCode(), ACC_31.getDescription());
        }
    }
}
