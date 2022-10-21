package com.sinem.service;

import com.sinem.dto.ActivateRequestDto;
import com.sinem.dto.NewCreateUserDto;
import com.sinem.dto.UpdateRequestDto;
import com.sinem.exception.UserServiceException;
import com.sinem.exception.ErrorType;
import com.sinem.mapper.IUserMapper;
import com.sinem.repository.IUserProfileRepository;
import com.sinem.repository.entity.UserProfile;
import com.sinem.repository.enums.Status;
import com.sinem.utility.JwtTokenManager;
import com.sinem.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {
    private final IUserProfileRepository userProfileRepository;
    private final JwtTokenManager tokenManager;
    public UserProfileService(IUserProfileRepository userProfileRepository,JwtTokenManager tokenManager) {
        super(userProfileRepository);
        this.userProfileRepository = userProfileRepository;
        this.tokenManager=tokenManager;
    }

    public UserProfile createUser(NewCreateUserDto dto){
        return userProfileRepository.save(IUserMapper.INSTANCE.toUserProfile(dto));

    }

    public boolean activatedUser(ActivateRequestDto dto) {
        Optional<UserProfile> userProfile = userProfileRepository.findOptionalByAuthid(dto.getId());
        if(userProfile.isPresent()){
            userProfile.get().setStatus(Status.ACTIVE);
            save(userProfile.get());
            return true;
        }else{
            throw new UserServiceException(ErrorType.USER_NOT_FOUND);
        }
    }

    public boolean updateUser(UpdateRequestDto dto) {
        Optional<Long> authid=tokenManager.getByIdFromToken(dto.getToken());
        UserProfile userProfile=IUserMapper.INSTANCE.toUserProfile(dto);
        if(authid.isPresent()) {
            Optional<UserProfile> userProfileDb = userProfileRepository.findOptionalByAuthid(authid.get());
                if(userProfileDb.isPresent()){
                    userProfileDb.get().setEmail(dto.getEmail());
                    userProfileDb.get().setAddress(dto.getAddress());
                    userProfileDb.get().setAbout(dto.getAbout());
                    userProfileDb.get().setName(dto.getName());
                    userProfileDb.get().setUsername(dto.getUsername());
                    userProfileDb.get().setPhone(dto.getPhone());
                    userProfileDb.get().setPhoto(dto.getPhoto());
                    save(userProfileDb.get());
                    return true;
                }else {
                    throw new UserServiceException(ErrorType.INTERNAL_ERROR);

                }
        }else {
            throw new UserServiceException(ErrorType.INTERNAL_ERROR);
        }


    }
}
