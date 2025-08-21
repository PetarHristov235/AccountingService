package com.accounting.demo.mapper;

import com.accounting.demo.entity.AccountEntity;
import com.accounting.demo.model.AccountDTO;
import com.accounting.demo.model.CreateAccountDTO;
import com.accounting.demo.model.UpdateAccountDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DateMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AccountMapper {

    @Mappings({
            @Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "toOffsetUtc"),
            @Mapping(source = "modifiedOn", target = "modifiedOn", qualifiedByName = "toOffsetUtc")
    })
    AccountDTO toDto(AccountEntity entity);

    List<AccountDTO> toDtoList(List<AccountEntity> entities);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdOn", ignore = true),
            @Mapping(target = "modifiedOn", ignore = true),
            @Mapping(target = "version", ignore = true)
    })
    AccountEntity toEntity(CreateAccountDTO dto);

    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "modifiedOn", ignore = true)
    @Mapping(target = "version", ignore = true)
    AccountEntity updateFromDto(UpdateAccountDTO dto, @MappingTarget AccountEntity entity);
}