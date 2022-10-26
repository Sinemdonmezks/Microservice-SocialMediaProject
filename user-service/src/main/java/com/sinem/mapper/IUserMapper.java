package com.sinem.mapper;

import com.sinem.dto.request.ActivateRequestDto;
import com.sinem.dto.request.NewCreateUserDto;
import com.sinem.dto.request.UpdateRequestDto;
import com.sinem.dto.response.UserProfileRedisResponseDto;
import com.sinem.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {
    IUserMapper INSTANCE= Mappers.getMapper(IUserMapper.class);

    UserProfile toUserProfile(final NewCreateUserDto toNewCreateUserDto);
    UserProfile toUserProfile(final ActivateRequestDto dto);
    UserProfile toUserProfile(final UpdateRequestDto dto);
    UserProfile toUserProfile(final UserProfileRedisResponseDto dto);
    UserProfileRedisResponseDto toUserProfileRedisResponseDto (final UserProfile profile);
}
