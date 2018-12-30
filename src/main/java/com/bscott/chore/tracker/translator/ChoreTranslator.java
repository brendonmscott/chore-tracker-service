package com.bscott.chore.tracker.translator;

import com.bscott.chore.tracker.domain.Chore;
import com.bscott.chore.tracker.dto.ChoreDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChoreTranslator extends ConfigurableMapper {

    private MapperFacade mapper;

    @Override
    protected void configure(MapperFactory factory) {

        factory.getConverterFactory().registerConverter(new LocalDateConverter());

        factory.classMap(Chore.class, ChoreDto.class)
                .byDefault()
                .register();

        mapper = factory.getMapperFacade();
    }

    public List<ChoreDto> toDtos(List<Chore> entityList) {

        if (entityList == null) {
            return new ArrayList<>();
        }

        List<ChoreDto> dtos = new ArrayList<>();

        for(Chore entity : entityList){
            dtos.add(toDto(entity));
        }

        return dtos;
    }

    public ChoreDto toDto(Chore entity) {
        return mapper.map(entity, ChoreDto.class);
    }

    public Chore toEntity(ChoreDto dto) {
        return mapper.map(dto, Chore.class);
    }
}
