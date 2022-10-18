package com.sinem.mapper;

import com.sinem.dto.request.LoginRequestDto;
import com.sinem.dto.request.RegisterRequestDto;
import com.sinem.dto.response.LoginResponseDto;
import com.sinem.repository.IAuthRepository;
import com.sinem.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {
    IAuthMapper INSTANCE= Mappers.getMapper(IAuthMapper.class);

    RegisterRequestDto toRegisterRequestDto(final Auth auth);
    Auth toAuth(final RegisterRequestDto dto);

    LoginRequestDto toLoginRequestDto(final Auth auth);
    Auth toAuth(final LoginRequestDto dto);

    LoginResponseDto toLoginResponseDto(final Auth auth);
    Auth toAuth(final LoginResponseDto dto);


}
