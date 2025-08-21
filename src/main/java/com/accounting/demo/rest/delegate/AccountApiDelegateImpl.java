package com.accounting.demo.rest.delegate;

import com.accounting.demo.api.AccountsApiDelegate;
import com.accounting.demo.model.AccountDTO;
import com.accounting.demo.model.CreateAccountDTO;
import com.accounting.demo.model.UpdateAccountDTO;
import com.accounting.demo.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class AccountApiDelegateImpl implements AccountsApiDelegate {

    AccountService accountService;

    @Override
    public ResponseEntity<AccountDTO> getAccountById(Long accountId) {
        return ResponseEntity.ok(accountService.getAccountById(accountId));
    }

    @Override
    public ResponseEntity<List<AccountDTO>> getAccountsList() {
        return ResponseEntity.ok(accountService.getAccountList());
    }

    @Override
    public ResponseEntity<AccountDTO> createAccount(CreateAccountDTO createAccountDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(createAccountDTO));
    }

    @Override
    public ResponseEntity<AccountDTO> updateExistingAccount(UpdateAccountDTO updateAccountDTO) {
        return ResponseEntity.ok(accountService.updateAccount(updateAccountDTO));
    }

    @Override
    public ResponseEntity<AccountDTO> freezeAccount(Long accountId, String name) {
        return ResponseEntity.ok(accountService.freezeAccount(accountId, name));
    }

    @Override
    public ResponseEntity<AccountDTO> unfreezeAccount(Long accountId, String name) {
        return ResponseEntity.ok(accountService.unfreezeAccount(accountId, name));
    }
}