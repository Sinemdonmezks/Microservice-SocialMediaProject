package com.sinem.utility;

import com.sinem.dto.response.UserProfileResponseDto;
import com.sinem.manager.IUserProfileManager;
import com.sinem.mapper.IUserMapper;
import com.sinem.repository.entity.UserProfile;
import com.sinem.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetAllData {
    private final IUserProfileManager userProfileManager;
    private final UserProfileService userProfileService;
    //@PostConstruct
    //verileri kaydettikten sonra kapatabiliriz
    public void init(){
        List<UserProfileResponseDto> userProfiles= userProfileManager.findAll().getBody();

        userProfileService.saveAll(userProfiles.stream()
                .map(dto -> IUserMapper.INSTANCE.toUserProfile(dto))
                .collect(Collectors.toList()));
    }
}
