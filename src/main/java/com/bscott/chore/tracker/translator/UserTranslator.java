package com.bscott.chore.tracker.translator;

import com.bscott.chore.tracker.domain.LoginCredentials;
import com.bscott.chore.tracker.domain.User;
import com.bscott.chore.tracker.dto.RegisterUserDto;
import com.bscott.chore.tracker.dto.UserDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserTranslator extends ConfigurableMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private MapperFacade mapper;

    public UserTranslator(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(MapperFactory factory) {

        factory.getConverterFactory().registerConverter(new LocalDateConverter());

        factory.classMap(User.class, UserDto.class)
                .byDefault()
                .register();

        mapper = factory.getMapperFacade();
    }

    public List<UserDto> toDtos(List<User> entityList){

        if (entityList == null) {
            return new ArrayList<>();
        }

        List<UserDto> dtos = new ArrayList<>();

        for (User entity : entityList) {
            dtos.add(toDto(entity));
        }

        return dtos;
    }

    public UserDto toDto(User entity) {
        return mapper.map(entity, UserDto.class);
    }

    public User toEntity(UserDto dto) {
//
//        if (dto != null && dto.getPassword() != null) {
//            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
//        }

        return mapper.map(dto, User.class);
    }

    public LoginCredentials getLoginCredentials(RegisterUserDto registerUserDto) {

        if (registerUserDto == null) {
            return null;
        }

        LoginCredentials loginCredentials = new LoginCredentials();
        loginCredentials.setUsername(registerUserDto.getUsername());
        loginCredentials.setPassword(registerUserDto.getPassword());

        return loginCredentials;
    }
}
