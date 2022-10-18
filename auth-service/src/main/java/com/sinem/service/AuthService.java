package com.sinem.service;

import com.sinem.dto.request.LoginRequestDto;
import com.sinem.dto.request.RegisterRequestDto;
import com.sinem.dto.response.LoginResponseDto;
import com.sinem.exception.AuthServiceException;
import com.sinem.exception.ErrorType;
import com.sinem.mapper.IAuthMapper;
import com.sinem.repository.IAuthRepository;
import com.sinem.repository.entity.Auth;
import com.sinem.repository.enums.Roles;
import com.sinem.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {

    private final IAuthRepository authRepository;
    public AuthService(IAuthRepository repository) {
        super(repository);
        this.authRepository=repository;
    }

    public Auth register(RegisterRequestDto dto){
        Auth auth= IAuthMapper.INSTANCE.toAuth(dto);//mapper ile dto yu auth a çevirip kaydettik

        if(userIsExist(dto.getNamesurname())){
            throw new AuthServiceException(ErrorType.LOGIN_ERROR_USERNAME_DUPLICATE);
        }else {
            if (dto.getAdmincode()!=null&&dto.getAdmincode().equals("Admin")){
                auth.setRole(Roles.ADMIN);
            }
            try{
                return save(auth);
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

      /*  try {
            if (dto.getAdmincode()!=null&&dto.getAdmincode().equals("Admin")){
                    auth.setRole(Roles.ADMIN);
            }
            return save(auth); //dto yu auth a çevirip kaydettik
        } catch (Exception e){
            throw new AuthServiceException(ErrorType.LOGIN_ERROR_USERNAME_DUPLICATE);
        }*/


}
