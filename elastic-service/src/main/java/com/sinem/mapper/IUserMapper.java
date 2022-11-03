package com.sinem.mapper;

import com.sinem.dto.response.UserProfileResponseDto;
import com.sinem.grapgql.model.UserProfileInput;
import com.sinem.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IUserMapper {

    IUserMapper INSTANCE= Mappers.getMapper(IUserMapper.class);

   // @Mapping(source= "id" , target= "userid")
    UserProfile toUserProfile(final UserProfileResponseDto dto);

    UserProfile toUserProfile(final UserProfileInput profileInput);

}
