package com.sinem.service;

import com.sinem.dto.NewCreateUserDto;
import com.sinem.dto.request.ActivateRequestDto;
import com.sinem.dto.request.LoginRequestDto;
import com.sinem.dto.request.RegisterRequestDto;
import com.sinem.dto.response.LoginResponseDto;
import com.sinem.dto.response.RegisterResponseDto;
import com.sinem.exception.AuthServiceException;
import com.sinem.exception.ErrorType;
import com.sinem.manager.IUserManager;
import com.sinem.mapper.IAuthMapper;
import com.sinem.repository.IAuthRepository;
import com.sinem.repository.entity.Auth;
import com.sinem.repository.enums.Roles;
import com.sinem.repository.enums.Status;
import com.sinem.utility.CodeGenerator;
import com.sinem.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService extends ServiceManager<Auth,Long> {

    private final IAuthRepository authRepository;
    private final IUserManager userManager;
    public AuthService(IAuthRepository repository,IUserManager userManager) {
        super(repository);
        this.authRepository=repository;
        this.userManager=userManager;
    }

    public RegisterResponseDto register(RegisterRequestDto dto){
        Auth auth= IAuthMapper.INSTANCE.toAuth(dto);//mapper ile dto yu auth a çevirip kaydettik

        if(userIsExist(dto.getNamesurname())){
            throw new AuthServiceException(ErrorType.LOGIN_ERROR_USERNAME_DUPLICATE);
        }else {
            if (dto.getAdmincode()!=null&&dto.getAdmincode().equals("Admin")){
                auth.setRole(Roles.ADMIN);
            }
            try{
                auth.setActivatedCode(CodeGenerator.generateCode(UUID.randomUUID().toString()));
                save(auth);
                userManager.createUser(NewCreateUserDto.builder()
                                .authid(auth.getId())
                                .email(auth.getEmail())
                                .username(auth.getNamesurname())
                                .build());
                        return IAuthMapper.INSTANCE.toRegisterResponseDto(auth);
            }catch (Exception e){
                throw new AuthServiceException(ErrorType.USER_NOT_CREATED);
            }
        }
    }
    public boolean userIsExist(String namesurname){

        return authRepository.existUsername(namesurname);
    }

    public Optional<LoginResponseDto> login (LoginRequestDto dto){
        Optional<Auth> auth=authRepository.findOptionalByEmailAndPassword(dto.getEmail(),dto.getPassword());
        if (auth.isPresent()){
            return Optional.of(IAuthMapper.INSTANCE.toLoginResponseDto(auth.get()));
        }else{
            throw new AuthServiceException(ErrorType.LOGIN_ERROR_WRONG);

        }
    }
    public boolean activateStatus(ActivateRequestDto dto) {

        Optional<Auth> auth=authRepository.findById(dto.getId());
        if (auth.isEmpty()){
            throw  new AuthServiceException(ErrorType.USER_NOT_FOUND);
        }
        if (auth.get().getActivatedCode().equals(dto.getActivatedCode())){
            auth.get().setStatus(Status.ACTIVE);
            save(auth.get());
            return true;
        }
        throw  new AuthServiceException(ErrorType.INVALID_ACTIVATE_CODE);
    }
}





      /*  try {
            if (dto.getAdmincode()!=null&&dto.getAdmincode().equals("Admin")){
                    auth.setRole(Roles.ADMIN);
            }
            return save(auth); //dto yu auth a çevirip kaydettik
        } catch (Exception e){
            throw new AuthServiceException(ErrorType.LOGIN_ERROR_USERNAME_DUPLICATE);
        }*/





