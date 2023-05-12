package com.springbootrestfuwebservices.mapper;

import com.springbootrestfuwebservices.dto.UserDto;
import com.springbootrestfuwebservices.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {
    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);
    // @Mapping(source = "email", target="emailAddressDto")        // MAPPER = Mappers.getMapper(AutoUserMapper.class);
    UserDto mappToUserDto(User user);

    User mapToUser(UserDto userDto);

}
