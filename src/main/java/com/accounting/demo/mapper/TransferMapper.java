package com.accounting.demo.mapper;

import com.accounting.demo.entity.AccountEntity;
import com.accounting.demo.entity.TransferEntity;
import com.accounting.demo.model.CreateTransferDTO;
import com.accounting.demo.model.TransferDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DateMapper.class})
public interface TransferMapper {

    @Mapping(target = "accountId", source = "account.id")
    @Mapping(target = "beneficiaryAccountId", source = "beneficiaryAccount.id")
    @Mapping(target = "createdOn", source = "createdOn", qualifiedByName = "toOffsetUtc")
    @Mapping(target = "modifiedOn", source = "modifiedOn", qualifiedByName = "toOffsetUtc")
    TransferDTO toDto(TransferEntity transferEntity);

    List<TransferDTO> toDtoList(List<TransferEntity> transferEntities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "account", source = "account")
    @Mapping(target = "beneficiaryAccount", source = "beneficiaryAccount")
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "modifiedOn", ignore = true)
    @Mapping(target = "amount", source = "dto.amount")
    @Mapping(target = "type", source = "dto.type")
    TransferEntity toEntity(CreateTransferDTO dto,
                            AccountEntity account,
                            AccountEntity beneficiaryAccount);
}