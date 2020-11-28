package com.bscott.chore.tracker.translator;

import com.bscott.chore.tracker.domain.User;
import com.bscott.chore.tracker.dto.SignUpRequestDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegistrationTranslator extends ConfigurableMapper {

    @Autowired
    private final PasswordEncoder passwordEncoder;

    private MapperFacade mapper;

    public RegistrationTranslator(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(MapperFactory factory) {

        factory.getConverterFactory().registerConverter(new LocalDateConverter());

        factory.classMap(User.class, SignUpRequestDto.class)
                .byDefault()
                .register();

        mapper = factory.getMapperFacade();
    }

    public SignUpRequestDto toDto(User entity) {
        return mapper.map(entity, SignUpRequestDto.class);
    }

    public User toEntity(SignUpRequestDto dto) {

        if (dto != null && dto.getCredentials().getPassword() != null) {
            dto.getCredentials().setPassword(passwordEncoder.encode(dto.getCredentials().getPassword()));
        }

        return mapper.map(dto, User.class);
    }
}
