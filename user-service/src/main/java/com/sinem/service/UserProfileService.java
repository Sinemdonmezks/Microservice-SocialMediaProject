package com.sinem.service;

import com.sinem.dto.NewCreateUserDto;
import com.sinem.mapper.IUserMapper;
import com.sinem.repository.IUserProfileRepository;
import com.sinem.repository.entity.UserProfile;
import com.sinem.utility.ServiceManager;
import org.mapstruct.control.MappingControl;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {
    private final IUserProfileRepository userProfileRepository;

    public UserProfileService(IUserProfileRepository userProfileRepository) {
        super(userProfileRepository);
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfile createUser(NewCreateUserDto dto){
        return userProfileRepository.save(IUserMapper.INSTANCE.toUserProfile(dto));

    }

}
