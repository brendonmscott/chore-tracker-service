package com.bscott.chore.tracker.translator;

import com.bscott.chore.tracker.domain.BannerMessage;
import com.bscott.chore.tracker.dto.BannerMessageDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BannerMessageTranslator extends ConfigurableMapper {

    private MapperFacade mapper;

    @Override
    protected void configure(MapperFactory factory) {

        factory.getConverterFactory().registerConverter(new LocalDateConverter());

        factory.classMap(BannerMessage.class, BannerMessageDto.class)
                .byDefault()
                .register();

        mapper = factory.getMapperFacade();
    }

    public List<BannerMessageDto> toDtos(List<BannerMessage> entityList) {

        if (entityList == null) {
            return new ArrayList<>();
        }

        List<BannerMessageDto> dtos = new ArrayList<>();

        for (BannerMessage entity : entityList) {
            dtos.add(toDto(entity));
        }

        return dtos;
    }

    public BannerMessageDto toDto(BannerMessage entity) {
        return mapper.map(entity, BannerMessageDto.class);
    }

    public BannerMessage toEntity(BannerMessageDto dto) {
        return mapper.map(dto, BannerMessage.class);
    }
}
