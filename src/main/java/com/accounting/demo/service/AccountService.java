package com.accounting.demo.service;

import com.accounting.demo.model.AccountDTO;
import com.accounting.demo.model.CreateAccountDTO;
import com.accounting.demo.model.UpdateAccountDTO;

import java.util.List;

public interface AccountService {
    List<AccountDTO> getAccountList();

    AccountDTO getAccountById(Long id);

    AccountDTO createAccount(CreateAccountDTO createAccountDTO);

    AccountDTO updateAccount(UpdateAccountDTO updateAccountDTO);

    AccountDTO freezeAccount(Long accountId, String name);

    AccountDTO unfreezeAccount(Long accountId, String name);
}
