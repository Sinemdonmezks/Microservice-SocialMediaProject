package com.sinem.mapper;

import com.sinem.dto.NewCreateUserDto;
import com.sinem.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {
    IUserMapper INSTANCE= Mappers.getMapper(IUserMapper.class);

    UserProfile toUserProfile(final NewCreateUserDto toNewCreateUserDto);
}
