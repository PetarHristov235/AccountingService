package com.accounting.demo.service;

import com.accounting.demo.entity.AccountEntity;
import com.accounting.demo.enumerator.AccountStatusEnum;
import com.accounting.demo.mapper.AccountMapper;
import com.accounting.demo.model.AccountDTO;
import com.accounting.demo.model.CreateAccountDTO;
import com.accounting.demo.model.UpdateAccountDTO;
import com.accounting.demo.repository.AccountRepository;
import com.accounting.demo.validator.AccountValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class AccountServiceImpl implements AccountService {
    AccountRepository accountRepository;
    AccountMapper accountMapper;
    AccountValidator accountValidator;

    @Override
    public List<AccountDTO> getAccountList() {
        List<AccountEntity> accountEntityList = accountRepository.findAllAccounts();
        return accountMapper.toDtoList(accountEntityList);
    }

    @Override
    public AccountDTO getAccountById(Long id) {
        Optional<AccountEntity> optAccountEntity = accountRepository.findById(id);
        accountValidator.validateAccountExists(optAccountEntity);
        return accountMapper.toDto(optAccountEntity.get());
    }

    @Override
    @Transactional
    public AccountDTO createAccount(CreateAccountDTO createAccountDTO) {
        accountValidator.validateCreateAccount(createAccountDTO);
        AccountEntity accountEntity = accountMapper.toEntity(createAccountDTO);
        accountEntity = accountRepository.save(accountEntity);
        return accountMapper.toDto(accountEntity);
    }

    @Override
    @Transactional
    public AccountDTO updateAccount(UpdateAccountDTO updateAccountDTO) {
        Optional<AccountEntity> optAccountEntity =
                accountRepository.findById(updateAccountDTO.getId());
        accountValidator.validateUpdateAccount(updateAccountDTO);
        return accountMapper.toDto(accountMapper.updateFromDto(updateAccountDTO,
                optAccountEntity.get()));
    }

    @Override
    @Transactional
    public AccountDTO freezeAccount(Long accountId, String name) {
        Optional<AccountEntity> optAccountEntity = accountRepository.findByNameAndId(name, accountId);

        accountValidator.validateFreezeAccount(optAccountEntity);

        AccountEntity accountEntity = optAccountEntity.get();
        accountEntity.setStatus(AccountStatusEnum.FROZEN);
        return accountMapper.toDto(accountEntity);
    }

    @Override
    @Transactional
    public AccountDTO unfreezeAccount(Long accountId, String name) {
        Optional<AccountEntity> optAccountEntity = accountRepository.findByNameAndId(name, accountId);

        accountValidator.validateUnfreezeAccount(optAccountEntity);

        AccountEntity accountEntity = optAccountEntity.get();
        accountEntity.setStatus(AccountStatusEnum.ACTIVE);
        return accountMapper.toDto(accountEntity);
    }
}
