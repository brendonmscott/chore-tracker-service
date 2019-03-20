package com.bscott.chore.tracker.translator;

import com.bscott.chore.tracker.domain.Reward;
import com.bscott.chore.tracker.dto.RewardDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RewardTranslator extends ConfigurableMapper {

    private MapperFacade mapper;

    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(Reward.class, RewardDto.class)
                .byDefault()
                .register();

        mapper = factory.getMapperFacade();
    }

    public List<RewardDto> toDtos(List<Reward> entityList){

        if (entityList == null) {
            return new ArrayList<>();
        }

        List<RewardDto> dtos = new ArrayList<>();

        for (Reward entity : entityList) {
            dtos.add(toDto(entity));
        }

        return dtos;
    }

    public RewardDto toDto(Reward entity) {
        return mapper.map(entity, RewardDto.class);
    }

    public Reward toEntity(RewardDto dto) {
        return mapper.map(dto, Reward.class);
    }
}
