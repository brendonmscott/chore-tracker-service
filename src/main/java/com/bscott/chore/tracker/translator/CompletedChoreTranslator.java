package com.bscott.chore.tracker.translator;

import com.bscott.chore.tracker.domain.CompletedChore;
import com.bscott.chore.tracker.dto.CompletedChoreDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompletedChoreTranslator extends ConfigurableMapper {

    private MapperFacade mapper;

    @Override
    protected void configure(MapperFactory factory) {

        factory.getConverterFactory().registerConverter(new LocalDateConverter());

        factory.classMap(CompletedChore.class, CompletedChoreDto.class)
                .byDefault()
                .register();

        mapper = factory.getMapperFacade();
    }

    public List<CompletedChoreDto> toDtos(List<CompletedChore> entityList) {

        if (entityList == null) {
            return new ArrayList<>();
        }

        List<CompletedChoreDto> dtos = new ArrayList<>();

        for(CompletedChore entity : entityList){
            dtos.add(toDto(entity));
        }

        return dtos;
    }

    public CompletedChoreDto toDto(CompletedChore entity) {
        return mapper.map(entity, CompletedChoreDto.class);
    }

    public CompletedChore toEntity(CompletedChoreDto dto) {
        return mapper.map(dto, CompletedChore.class);
    }
}
