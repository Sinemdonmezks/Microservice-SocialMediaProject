package com.sinem.mapper;

import com.sinem.dto.NewCreateUserDto;
import com.sinem.dto.request.ActivateRequestDto;
import com.sinem.dto.request.LoginRequestDto;
import com.sinem.dto.request.RegisterRequestDto;
import com.sinem.dto.response.LoginResponseDto;
import com.sinem.dto.response.RegisterResponseDto;
import com.sinem.dto.response.RoleResponseDto;
import com.sinem.repository.entity.Auth;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-26T16:44:50+0300",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.4.1 (Eclipse Adoptium)"
)
@Component
public class IAuthMapperImpl implements IAuthMapper {

    @Override
    public RegisterRequestDto toRegisterRequestDto(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        RegisterRequestDto.RegisterRequestDtoBuilder registerRequestDto = RegisterRequestDto.builder();

        registerRequestDto.username( auth.getUsername() );
        registerRequestDto.password( auth.getPassword() );
        registerRequestDto.email( auth.getEmail() );

        return registerRequestDto.build();
    }

    @Override
    public Auth toAuth(RegisterRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Auth.AuthBuilder auth = Auth.builder();

        auth.username( dto.getUsername() );
        auth.password( dto.getPassword() );
        auth.email( dto.getEmail() );

        return auth.build();
    }

    @Override
    public LoginRequestDto toLoginRequestDto(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        LoginRequestDto.LoginRequestDtoBuilder loginRequestDto = LoginRequestDto.builder();

        loginRequestDto.email( auth.getEmail() );
        loginRequestDto.password( auth.getPassword() );

        return loginRequestDto.build();
    }

    @Override
    public Auth toAuth(LoginRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Auth.AuthBuilder auth = Auth.builder();

        auth.password( dto.getPassword() );
        auth.email( dto.getEmail() );

        return auth.build();
    }

    @Override
    public LoginResponseDto toLoginResponseDto(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        LoginResponseDto.LoginResponseDtoBuilder loginResponseDto = LoginResponseDto.builder();

        loginResponseDto.id( auth.getId() );
        loginResponseDto.username( auth.getUsername() );
        loginResponseDto.email( auth.getEmail() );
        loginResponseDto.role( auth.getRole() );

        return loginResponseDto.build();
    }

    @Override
    public Auth toAuth(LoginResponseDto dto) {
        if ( dto == null ) {
            return null;
        }

        Auth.AuthBuilder auth = Auth.builder();

        auth.id( dto.getId() );
        auth.username( dto.getUsername() );
        auth.email( dto.getEmail() );
        auth.role( dto.getRole() );

        return auth.build();
    }

    @Override
    public RegisterResponseDto toRegisterResponseDto(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        RegisterResponseDto.RegisterResponseDtoBuilder registerResponseDto = RegisterResponseDto.builder();

        registerResponseDto.id( auth.getId() );
        registerResponseDto.username( auth.getUsername() );
        registerResponseDto.activatedCode( auth.getActivatedCode() );

        return registerResponseDto.build();
    }

    @Override
    public Auth toAuth(RegisterResponseDto dto) {
        if ( dto == null ) {
            return null;
        }

        Auth.AuthBuilder auth = Auth.builder();

        auth.id( dto.getId() );
        auth.username( dto.getUsername() );
        auth.activatedCode( dto.getActivatedCode() );

        return auth.build();
    }

    @Override
    public ActivateRequestDto toActivetedRequestDto(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        ActivateRequestDto.ActivateRequestDtoBuilder activateRequestDto = ActivateRequestDto.builder();

        activateRequestDto.id( auth.getId() );
        activateRequestDto.activatedCode( auth.getActivatedCode() );

        return activateRequestDto.build();
    }

    @Override
    public NewCreateUserDto toNewCreateUserDto(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        NewCreateUserDto.NewCreateUserDtoBuilder newCreateUserDto = NewCreateUserDto.builder();

        newCreateUserDto.username( auth.getUsername() );
        newCreateUserDto.email( auth.getEmail() );

        return newCreateUserDto.build();
    }

    @Override
    public Auth toAuth(NewCreateUserDto dto) {
        if ( dto == null ) {
            return null;
        }

        Auth.AuthBuilder auth = Auth.builder();

        auth.username( dto.getUsername() );
        auth.email( dto.getEmail() );

        return auth.build();
    }

    @Override
    public RoleResponseDto toRoleResponseDto(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        RoleResponseDto.RoleResponseDtoBuilder roleResponseDto = RoleResponseDto.builder();

        roleResponseDto.id( auth.getId() );
        roleResponseDto.username( auth.getUsername() );
        roleResponseDto.role( auth.getRole() );

        return roleResponseDto.build();
    }
}
