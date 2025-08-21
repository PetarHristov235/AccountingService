package com.accounting.demo.validator;

import com.accounting.demo.entity.AccountEntity;
import com.accounting.demo.model.CreateAccountDTO;
import com.accounting.demo.model.UpdateAccountDTO;

import java.util.Optional;

public interface AccountValidator {
    void validateAccountExists(Optional<AccountEntity> optAccountEntity);

    void validateFreezeAccount(Optional<AccountEntity> optAccountEntity);

    void validateUnfreezeAccount(Optional<AccountEntity> optAccountEntity);

    void validateCreateAccount(CreateAccountDTO createAccountDTO);

    void validateUpdateAccount(UpdateAccountDTO createAccountDTO);
}
