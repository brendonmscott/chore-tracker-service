package com.bscott.chore.tracker.translator;

import com.bscott.chore.tracker.domain.Account;
import com.bscott.chore.tracker.dto.AccountDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountTranslator extends ConfigurableMapper {

    private MapperFacade mapper;

    @Override
    protected void configure(MapperFactory factory) {

        factory.getConverterFactory().registerConverter(new LocalDateConverter());

        factory.classMap(Account.class, AccountDto.class)
                .byDefault()
                .register();

        mapper = factory.getMapperFacade();
    }

    public List<AccountDto> toDtos(List<Account> entityList) {

        if (entityList == null) {
            return new ArrayList<>();
        }

        List<AccountDto> dtos = new ArrayList<>();

        for (Account entity : entityList) {
            dtos.add(toDto(entity));
        }

        return dtos;
    }

    public AccountDto toDto(Account entity) {
        return mapper.map(entity, AccountDto.class);
    }

    public Account toEntity(AccountDto dto) {
        return mapper.map(dto, Account.class);
    }
}
